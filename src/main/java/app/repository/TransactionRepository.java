package app.repository;

import app.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: aconstantin
 * Date: 5/19/14 7:45 PM
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
