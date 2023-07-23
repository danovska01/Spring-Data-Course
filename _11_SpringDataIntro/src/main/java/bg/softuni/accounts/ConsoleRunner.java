package bg.softuni.accounts;

import bg.softuni.accounts.models.Account;
import bg.softuni.accounts.models.User;
import bg.softuni.accounts.repositories.AccountRepository;
import bg.softuni.accounts.repositories.UserRepository;
import bg.softuni.accounts.services.AccountService;
import bg.softuni.accounts.services.UserService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final AccountService accountService;

    private final UserService userService;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;


    public ConsoleRunner(AccountService accountService, UserService userService,
                         AccountRepository accountRepository,
                         UserRepository userRepository) {
        this.accountService = accountService;
        this.userService = userService;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {
//      User someuser = new User("someuse1r", 20);
//        User user = new User("Pesho1", 33);
//      userService.register(someuser);
//      userService.register(user);

      User owner = userRepository.findByUsername("Pesho1");
      Account account = accountRepository.findById(1);
      account.setOwner(owner);
        accountRepository.save(account);



//      accountService.depositMoney(BigDecimal.valueOf(113000), 2L);
//       accountService.depositMoney(BigDecimal.valueOf(3000), 1L);
//      accountService.withdrawMoney(BigDecimal.valueOf(200), 1L);
//      accountService.depositMoney(BigDecimal.valueOf(9000), 1L);
//      accountService.withdrawMoney(BigDecimal.valueOf(200), 1L);






    }


}
