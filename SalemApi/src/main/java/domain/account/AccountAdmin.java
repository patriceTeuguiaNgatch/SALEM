package domain.account;

import domain.contribution.Contribution;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class AccountAdmin extends Account {

    public AccountAdmin(AccountId accountId, String email, String password, String firstName, String lastName, String telephoneNumber, String role) {
        super(accountId, email, password, firstName, lastName, telephoneNumber, role);
    }

    public List<Contribution> findContributionByPeriod(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    public Contribution findContributionById(UUID contributtionNumber) throws Exception {
        return null;
    }

}
