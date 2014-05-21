package app.repository;


import app.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: aconstantin
 * Date: 5/19/14 7:29 PM
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findByNumber(String number);
    List<Account> findByName(String name);
}
