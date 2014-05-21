package app.ui;

import app.domain.Account;
import app.domain.Transaction;
import app.service.AccountService;
import app.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * User: aconstantin
 * Date: 5/20/14 7:22 PM
 */

@Controller
@RequestMapping("app")
public class TransferController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransferService transferService;

    @RequestMapping(value = "transfer", method = RequestMethod.POST)
    @ResponseBody
    public boolean transfer(
            @RequestParam(value = "fromAccountNumber", required = true) final String fromAccountNumber,
            @RequestParam(value = "toAccountNumber", required = true) final String toAccountNumber,
            @RequestParam(value = "moneyAmount", required = true) final String moneyAmount) {

        try {
            final BigDecimal amount = new BigDecimal(moneyAmount);

            Transaction transaction = new Transaction() {{
                setMoneyAmount(amount);
                setBeneficiaryAccountNumber(toAccountNumber);
                setPayerAccountNumber(fromAccountNumber);
            }};

            transferService.transferAmount(transaction);


            Account payerAccount = accountService.findByNumber(fromAccountNumber);
            Account beneficiaryAccount = accountService.findByNumber(toAccountNumber);

            System.out.println(payerAccount);
            System.out.println(beneficiaryAccount);

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }



}
