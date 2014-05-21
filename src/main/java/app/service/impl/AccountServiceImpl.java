package app.service.impl;

import app.domain.Account;
import app.repository.AccountRepository;
import app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findByNumber(String number) {
        List<Account> accounts = accountRepository.findByNumber(number);
        if (accounts == null || accounts.size() == 0) {
            String message = "No account found for number " + number + ".";
            System.out.println(message);
            throw new EmptyResultDataAccessException(message, 1);
        }
        if (accounts.size() > 1) {
            String message = "More than one account found for number" + number + ".";
            System.out.println(message);
            throw new IncorrectResultSizeDataAccessException(message, 1);
        }
        return accounts.get(0);
    }

    @Override
    public List<Account> findByName(String name) {
        return accountRepository.findByName(name);
    }

    @Override
    public BigDecimal getAmount(String number) {
        Account account = findByNumber(number);
        return account.getMoneyAmount();  //To change body of implemented methods use File | Settings | File Templates.

    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }




}