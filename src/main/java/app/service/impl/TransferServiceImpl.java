package app.service.impl;

import app.domain.Account;
import app.domain.Transaction;
import app.service.AccountService;
import app.service.TransactionService;
import app.service.TransferService;
import app.util.TransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;



    @Override
    public void transferAmount(Transaction transaction) {

        TransactionValidator.validate(transaction);

        Account payerAccount = accountService.findByNumber(transaction.getPayerAccountNumber());
        Account beneficiaryAccount = accountService.findByNumber(transaction.getBeneficiaryAccountNumber());
        BigDecimal amount = transaction.getMoneyAmount();

        payerAccount.setMoneyAmount(payerAccount.getMoneyAmount().subtract(amount));
        beneficiaryAccount.setMoneyAmount(beneficiaryAccount.getMoneyAmount().add(amount));


        accountService.save(payerAccount);
        accountService.save(beneficiaryAccount);

        transactionService.save(transaction);
    }

}
