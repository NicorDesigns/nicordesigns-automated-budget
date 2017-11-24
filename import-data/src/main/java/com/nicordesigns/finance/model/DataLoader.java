package com.nicordesigns.finance.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

@Service
public class DataLoader {

    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);


    private BankingTransactionRepository bankingTransactionRepository;

    private CategorizationRepository categorizationRepository;

    private CategoryRepository categoryRepository;

    private MerchantRepository merchantRepository;

    @Autowired
    public DataLoader(BankingTransactionRepository bankingTransactionRepository, CategorizationRepository categorizationRepository, CategoryRepository categoryRepository, MerchantRepository merchantRepository) {
        this.bankingTransactionRepository = bankingTransactionRepository;
        this.categorizationRepository = categorizationRepository;
        this.categoryRepository = categoryRepository;
        this.merchantRepository = merchantRepository;
    }

    @PostConstruct
    private void loadData() throws IOException {
        //Read the Categories in from a JSON file here

        ObjectMapper mapper = new ObjectMapper();

        Category category = new Category("Name", "1");

        //Object to JSON in file
        mapper.writeValue(new File("D:\\nicordesigns-automated-budget\\import-data\\src\\main\\resources\\category.json"), category);

        //Object to JSON in String
        String jsonInString = mapper.writeValueAsString(category);
        LOG.info(jsonInString);


        //Read the Merchants in from a JSON file here

        Merchant merchant = new Merchant("PayyName", "Mechant Name", 2);
        //Object to JSON in file
        mapper.writeValue(new File("D:\\nicordesigns-automated-budget\\import-data\\src\\main\\resources\\merchant.json"), merchant);

        jsonInString = mapper.writeValueAsString(merchant);
        LOG.info(jsonInString);



        Categorization categorization = new Categorization("Gas Station", "Auto Transport");

        categorizationRepository.save(categorization);

        BankingTransaction bankingTransaction = new BankingTransaction("Esso", "Esso", "Type", Calendar.getInstance(), BigDecimal.valueOf(2000.00));
        bankingTransaction.setCategorization(categorization);


        bankingTransactionRepository.save(bankingTransaction);

    }
}
