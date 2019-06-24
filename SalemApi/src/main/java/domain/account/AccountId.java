package domain.account;

import java.io.Serializable;
import java.util.Objects;

public class AccountId implements Serializable {

    private String accountId;

    public AccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return this.accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountId accountId1 = (AccountId) o;
        return Objects.equals(accountId, accountId1.accountId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(accountId);
    }
}
