package service.assembler;

import domain.account.Account;
import service.dto.AccountDto;
import service.dto.AccountsListDtos;

import java.util.List;

public class AccountAssembler {

    public AccountDto create(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.email = account.getEmail();
        accountDto.firstName = account.getFirstName();
        accountDto.lastName = account.getLastName();
        accountDto.telephoneNumber = account.getTelephoneNumber();
        return accountDto;
    }

    public AccountsListDtos createList(List<Account> accounts) {
        AccountsListDtos accountsListDtos = new AccountsListDtos();
        for(Account account : accounts){
            AccountDto accountDto = this.create(account);
            accountsListDtos.accountsDtos.add(accountDto);
        }
        return accountsListDtos;
    }
}
