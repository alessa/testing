package app.service.impl;

import app.domain.Transaction;
import app.repository.TransactionRepository;
import app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
