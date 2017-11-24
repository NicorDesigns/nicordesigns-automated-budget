package com.nicordesigns.finance.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CalculatorTest {

    /**
     * Date format used for TxnDate
     */
    public static final String OUTPUT_FILE_URL =
            "C:\\Users\\Nico\\Dropbox\\AutomatedBudget12112015\\automation-1.0-SNAPSHOT\\Budget07_01_17.xls";
    //C:\Users\Nico\Dropbox\AutomatedBudget12112015\automation-1.0-SNAPSHOT

    public static final String INPUT_FILE_URL = "C:\\Users\\Nico\\Dropbox\\AutomatedBudget12112015\\automation-1" +
            ".0-SNAPSHOT\\Export.csv";

    public static final String START_DATE = "2017-06-15";
    public static final String END_DATE = "2017-07-01";

    @Autowired
    private TransactionReader transactionReader;

    @Autowired
    private BankingTransactionRepository bankingTransactionRepository;

    @Before
    public void setUp() throws Exception {

        transactionReader = new TransactionReader(bankingTransactionRepository);
        transactionReader.setInputCsvFile(INPUT_FILE_URL);

        transactionReader.setStartTxnDate(START_DATE);
        transactionReader.setEndTxnDate(END_DATE);
    }

    @Test
    public void testgetAccountTransactionsCSV() {
        transactionReader.populateAccountTransactionsFromCSV();
    }

}
