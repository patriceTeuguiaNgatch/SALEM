package service.account;


import domain.account.AccountId;

public class AccountIdGenerator {

    private static final int ZERO = 0;
//    private static final int ONE = 1;
    private static final String SPACE = " ";

//    private AtomicLong atomicLong;

    public AccountIdGenerator() {
//    this.atomicLong = new AtomicLong(ONE);
    }

    public AccountId createAccountId(String firstName, String lastName){
        return createInitials(firstName, lastName);
    }

    private AccountId createInitials(String firstName, String lastName) {
        String [] partsLastName = lastName.split(SPACE);
        String partLastNameONE = partsLastName[ZERO];
        String [] partFirstName = firstName.split(SPACE);
        String partFirstNameOne = partFirstName[ZERO];
        AccountId accountId = new AccountId((partFirstNameOne + partLastNameONE).toUpperCase());
        return  accountId;
    }


    private String createInitialsNumber() {
        return String.valueOf(createInitialsNumber());
    }
}
