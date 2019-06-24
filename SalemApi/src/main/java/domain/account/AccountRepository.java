package domain.account;

import infrastructure.exception.AccountEmailAlreadyExistException;

import java.util.List;

public interface AccountRepository {

    void verifiedEmailIsNotPresent(String email) throws  AccountEmailAlreadyExistException;

    Account authentificateAccount(String email, String password) throws Exception;

    void save(Account account);

    List<Account> findAllAccount();
}
