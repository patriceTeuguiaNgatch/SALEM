package infrastructure.account;

import domain.account.AccountId;
import domain.account.Account;

import domain.account.AccountRepository;
import infrastructure.exception.AccountEmailAlreadyExistException;
import infrastructure.exception.AuthenticationParametersDontMatchException;
import infrastructure.exception.InvalidEmailException;
import service.account.AccountService;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class InMemoryAccountRepository implements AccountRepository {

    private Logger logger = Logger.getLogger(AccountService.class.getName());

    private HashMap<AccountId, Account> accountHashMap;

    public InMemoryAccountRepository(){
        this.accountHashMap = new HashMap<>();
    }

    @Override
    public void verifiedEmailIsNotPresent(String email) throws AccountEmailAlreadyExistException {
        if(IsAccountPresent(email)){

            logger.warning("Account email already exist in repository");

            throw new AccountEmailAlreadyExistException(email);
        }
    }

    @Override
    public Account authentificateAccount(String email, String password) throws Exception {
        this.validateAccountEmailPresent(email);
        Account account = this.validateAuthentificationParameters(email, password);
        return account;
    }


    @Override
    public void save(Account account){
        this.accountHashMap.put(account.getAccountId(), account);
    }

    @Override
    public List<Account> findAllAccount() {

        logger.info("find all account in repository");

        List<Account> accounts = new ArrayList<>();
        for (Map.Entry<AccountId, Account> entry : accountHashMap.entrySet()) {
            Account account = entry.getValue();
            accounts.add(account);
        }
        return accounts;
    }

    private void validateAccountEmailPresent(String email) throws InvalidEmailException {
        if(!IsAccountPresent(email)){
            throw new InvalidEmailException(email);
        }
    }

    private Account validateAuthentificationParameters(String email, String password) throws AuthenticationParametersDontMatchException, AccountNotFoundException {
        Account account = this.findAccountByEmail(email);
        if(!account.hasPassword(password)){
            throw new AuthenticationParametersDontMatchException();
        }
        return account;
    }

    private boolean IsAccountPresent(String email) {
        boolean isPresent = false;
        for(Map.Entry<AccountId, Account> entry : accountHashMap.entrySet()) {
            Account account = entry.getValue();
            if (account.hasEmail(email)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    private Account findAccountByEmail(String email) throws AccountNotFoundException {
        Account account = null;
        for (Map.Entry mapElement : accountHashMap.entrySet()) {
            AccountId accountIdCurrent = (AccountId) mapElement.getKey();
            if(accountIdCurrent.toString().equals(email)){
                account = this.accountHashMap.get(accountIdCurrent);
                break;
            }
        }
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }

}
