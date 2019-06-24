import api.AccountResource;
import api.AccountResourceValidator;
import api.mapper.BadEmailAddressExceptionMapper;
import api.mapper.BadPasswordExceptionMapper;
import domain.account.Account;
import domain.account.AccountRepository;
import domain.messageDevice.MessageSender;
import http.CORSResponseFilter;
import infrastructure.account.AccountDevDataFactory;
import infrastructure.account.InMemoryAccountRepository;
import infrastructure.mapper.AccountEmailAlreadyExistExceptionMapper;
import infrastructure.mapper.AuthenticationParametersDontMatchExceptionMapper;
import infrastructure.mapper.InvalidEmailExceptionMapper;
import infrastructure.messageDevice.EmailSender;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import service.account.AccountFactory;
import service.account.AccountIdGenerator;
import service.account.AccountService;
import service.assembler.AccountAssembler;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SalemApi {

    private static int PORT = 8081;
    private static boolean isDev = true;

    public static void main(String[] args) throws Exception{

        // Setup resources (API)

        AccountResource accountResource = createAccountResource();

        // Setup API context (JERSEY + JETTY)

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/api/salem");
        ResourceConfig resourceConfig = ResourceConfig.forApplication(new Application() {
            @Override
            public Set<Object> getSingletons() {
                HashSet<Object> resouces = new HashSet<>();

                // Add resources to context
                resouces.add(accountResource);
                return resouces;
            }
        });

        resourceConfig.register(CORSResponseFilter.class);
        setupExceptionMappers(resourceConfig);

        ServletContainer servletContainer  = new ServletContainer(resourceConfig);
        ServletHolder servletHolder = new ServletHolder(servletContainer);
        contextHandler.addServlet(servletHolder, "/*");

        // Setup http server

        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        contextHandlerCollection.setHandlers(new Handler[] {contextHandler});
        Server server = new Server(PORT);
        server.setHandler(contextHandler);

        try {
            server.start();
            server.join();
            } finally {

            server.destroy();
        }
    }

    private static void setupExceptionMappers(ResourceConfig resourceConfig) {
        resourceConfig.register(new BadEmailAddressExceptionMapper());
        resourceConfig.register(new BadPasswordExceptionMapper());
        resourceConfig.register(new AccountEmailAlreadyExistExceptionMapper());
        resourceConfig.register(new InvalidEmailExceptionMapper());
        resourceConfig.register(new AuthenticationParametersDontMatchExceptionMapper());
    }

    private static AccountResource createAccountResource(){
        AccountIdGenerator accountIdGenerator = new AccountIdGenerator();
        AccountResourceValidator accountResourceValidator = new AccountResourceValidator();
        AccountRepository inMemoryAccountRepository = new InMemoryAccountRepository();
        MessageSender emailSender = new EmailSender();


        if(isDev){
            AccountDevDataFactory accountDevDataFactory = new AccountDevDataFactory();
            List<Account> accounts = accountDevDataFactory.createMockData();
            accounts.stream().forEach(inMemoryAccountRepository::save);
        }

        AccountFactory accountFactory = new AccountFactory(accountIdGenerator);
        AccountAssembler accountAssembler = new AccountAssembler();
        AccountService accountService  = new AccountService(inMemoryAccountRepository, accountFactory, emailSender, accountAssembler);
        AccountResource accountResource = new AccountResource(accountService, accountResourceValidator);
        return accountResource;
    }
}
