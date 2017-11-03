package com.nicordesigns.finance.model;

import org.junit.Before;
import org.junit.Test;

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

    @Before
    public void setUp() throws Exception {

        TransactionReader.setInputCsvFile(INPUT_FILE_URL);

        TransactionReader.setStartTxnDate(START_DATE);
        TransactionReader.setEndTxnDate(END_DATE);
    }

    @Test
    public void testgetAccountTransactionsCSV() {
        TransactionReader.populateAccountTransactionsFromCSV();
    }

}
