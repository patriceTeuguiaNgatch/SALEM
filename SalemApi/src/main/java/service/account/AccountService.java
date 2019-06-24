package service.account;

import api.dto.AccountLoginCredentialsDto;
import api.dto.VolunteerAccountDto;
import domain.account.Account;
import domain.account.AccountId;
import domain.account.AccountRepository;
import domain.messageDevice.MessageSender;
import infrastructure.exception.AccountEmailAlreadyExistException;
import service.assembler.AccountAssembler;
import service.dto.AccountsListDtos;
import service.exception.AddressMailUnreachableException;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.logging.Logger;

public class AccountService {

    private Logger logger = Logger.getLogger(AccountService.class.getName());

    private AccountRepository accountRepository;
    private AccountFactory accountFactory;
    private MessageSender messageSender;
    private AccountAssembler accountAssembler;

    public AccountService(AccountRepository accountRepository, AccountFactory accountFactory, MessageSender messageSender, AccountAssembler accountAssembler){
    this.accountFactory = accountFactory;
    this.accountRepository = accountRepository;
    this.messageSender = messageSender;
    this.accountAssembler = accountAssembler;
    }

    public String createVolunteerAccount(VolunteerAccountDto volunteerAccountDto) throws AccountEmailAlreadyExistException, AddressMailUnreachableException {

        logger.info("create  a volunteer account");

        this.accountRepository.verifiedEmailIsNotPresent(volunteerAccountDto.email);
        Account account = this.accountFactory.create(volunteerAccountDto.email, volunteerAccountDto.password, volunteerAccountDto.firstName, volunteerAccountDto.lastName,volunteerAccountDto.telephoneNumber, volunteerAccountDto.role);
        this.accountRepository.save(account);
        String emailText = account.formateEmailTextAccountSuccessCreation();
        this.sendEmail(volunteerAccountDto.email, emailText);
        AccountId accountId = account.getAccountId();
        return accountId.toString();
    }

    public Account authentificateAccount(AccountLoginCredentialsDto accountLoginCredentialsDto) throws Exception {
        return this.accountRepository.authentificateAccount(accountLoginCredentialsDto.email, accountLoginCredentialsDto.password);
    }

    public void sendEmail(String email , String emailText) throws AddressMailUnreachableException {
        try{
            this.messageSender.sendMessage(email, emailText);
        }catch (Exception exception) {
            throw new AddressMailUnreachableException(email);
        }
    }

    public AccountsListDtos findAllAccount() {
        AccountsListDtos accountsListDtos = new AccountsListDtos();
        try{
            List<Account> accounts = this.accountRepository.findAllAccount();
            accountsListDtos = this.accountAssembler.createList(accounts);
        } catch (Exception exception) {
         //   throw new ;
        }
        return accountsListDtos;
    }
}
