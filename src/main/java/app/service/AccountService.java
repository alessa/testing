package app.service;

import app.domain.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    Account findByNumber(String number);

    Account save(Account account);

    List<Account> findByName(String name);

    BigDecimal getAmount(String number);

}
