package app.ui;

import app.domain.Transaction;
import app.service.TransactionService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * User: aconstantin
 * Date: 5/20/14 7:32 PM
 */

@Controller
@RequestMapping("app")
public class TransactionController {


    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "transaction", method = RequestMethod.POST)
    @ResponseBody
    public Transaction transaction(
            @RequestBody @NotEmpty Transaction transaction) {
        return transactionService.save(transaction);

    }
}
