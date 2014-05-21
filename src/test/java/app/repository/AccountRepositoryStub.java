package app.repository;

import app.domain.Account;

import java.util.List;

/**
 * User: aconstantin
 * Date: 5/21/14 12:44 PM
 */
public class AccountRepositoryStub {

    List<Account> list;

    public List<Account> getList() {
        return list;
    }

    public void setList(List<Account> list) {
        this.list = list;
    }
}
