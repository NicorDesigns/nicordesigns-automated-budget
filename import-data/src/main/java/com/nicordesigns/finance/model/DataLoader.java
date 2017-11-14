package com.nicordesigns.finance.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Service
public class DataLoader {

    private BankingTransactionRepository bankingTransactionRepository;

    private CategorizationRepository categorizationRepository;

    @Autowired
    public DataLoader(BankingTransactionRepository bankingTransactionRepository, CategorizationRepository categorizationRepository) {
        this.bankingTransactionRepository = bankingTransactionRepository;
        this.categorizationRepository = categorizationRepository;
    }

    @PostConstruct
    private void loadData() {
        Categorization categorization = new Categorization("Gas Station", "Auto Transport");
        categorizationRepository.save(categorization);

        BankingTransaction bankingTransaction = new BankingTransaction("Esso", "Esso", "Type", Calendar.getInstance(), BigDecimal.valueOf(2000.00));
        bankingTransaction.setCategorization(categorization);
        bankingTransactionRepository.save(bankingTransaction);

    }
}
