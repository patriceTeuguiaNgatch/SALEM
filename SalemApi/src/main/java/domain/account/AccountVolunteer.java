package domain.account;

import domain.contribution.Contribution;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountVolunteer extends Account {

    private List<Contribution> contributions;

    public AccountVolunteer(AccountId accountId, String email, String password, String firstName, String lastName, String telephoneNumber, String role){
        super(accountId, email, password, firstName, lastName, telephoneNumber, role);
        this.contributions = new ArrayList<>();
    }

    public void saveContribution(Contribution contribution){
        this.contributions.add(contribution);
    }

    public  List<Contribution> findContributionByPeriod(LocalDate startDate, LocalDate endDate){
        return null;
    }

}
