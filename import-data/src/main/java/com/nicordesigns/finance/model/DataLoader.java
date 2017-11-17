package com.nicordesigns.finance.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
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
    private void loadData() throws IOException {
        //Read the Categories in from a JSON file here

        ObjectMapper mapper = new ObjectMapper();
        Category obj = new Category("Name", "1");

        //Object to JSON in file
        mapper.writeValue(new File("c:\\file.json"), obj);

        //Object to JSON in String
        String jsonInString = mapper.writeValueAsString(obj);

        //Read the Merchants in from a JSON file here


        Categorization categorization = new Categorization("Gas Station", "Auto Transport");

        categorizationRepository.save(categorization);

        BankingTransaction bankingTransaction = new BankingTransaction("Esso", "Esso", "Type", Calendar.getInstance(), BigDecimal.valueOf(2000.00));
        bankingTransaction.setCategorization(categorization);
        bankingTransactionRepository.save(bankingTransaction);

    }
}
