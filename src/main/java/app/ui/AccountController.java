package app.ui;

import app.domain.Account;
import app.service.AccountService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: aconstantin
 * Date: 5/19/14 8:52 PM
 */
@Controller
@RequestMapping("app")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "accountsByName", method = RequestMethod.POST)
    @ResponseBody
    public List<Account> getAccountsByName(
            @RequestParam(value = "name", required = true)  String name) {
        return accountService.findByName(name);

    }

    @RequestMapping(value = "account", method = RequestMethod.POST)
    @ResponseBody
    public Account getAccountByNumber(
            @RequestBody @NotEmpty  Account account) {
        return accountService.findByNumber(account.getNumber());

    }



    //for TDD
    @RequestMapping(value = "getAmount", method = RequestMethod.POST)
    @ResponseBody
    public BigDecimal getAmount() {
       return null;
    }



}
