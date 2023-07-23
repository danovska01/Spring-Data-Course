package bg.softuni.accounts.repositories;

import bg.softuni.accounts.models.Account;
import bg.softuni.accounts.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findById(long id);
}
