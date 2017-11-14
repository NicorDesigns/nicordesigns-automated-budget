package com.nicordesigns.finance.model;


import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

@Component
public class TransactionReader implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionReader.class);
    private static List<BankingTransaction> csvTransactions = null;
    private static BigDecimal availableBalance;
    private static BigDecimal ledgerBalance;
    private static String inputCsvFile;
    private static String startTxnDate;
    private static String endTxnDate;

    @Autowired
    BankingTransactionRepository bankingTransactionRepository;

    public static String getInputCsvFile() {
        return inputCsvFile;
    }

    public static void setInputCsvFile(String inputCsvFile) {
        TransactionReader.inputCsvFile = inputCsvFile;
    }

    public static String getStartTxnDate() {
        return startTxnDate;
    }

    public static void setStartTxnDate(String startTxnDate) {
        TransactionReader.startTxnDate = startTxnDate;
    }

    public static String getEndTxnDate() {
        return endTxnDate;
    }

    public static void setEndTxnDate(String endTxnDate) {
        TransactionReader.endTxnDate = endTxnDate;
    }

    public static void main(String[] args) {
        LOG.info(String.format("ImportDataApplication.main(%s)", Arrays.toString(args)));
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("C", "Credit Union", false, "use file downloaded from Credit Union");
        options.addOption("Q", "Quicken", false, "use csv file downloaded from Quicken");

        try {

            CommandLine commandLine = parser.parse(options, args);

            int numArgs = commandLine.getArgs().length;
            LOG.info(String.format("numArgs=(%s)", numArgs));

            if (numArgs ==  3) {
                setInputCsvFile(commandLine.getArgs()[0]);
                setStartTxnDate(commandLine.getArgs()[1]);
                setEndTxnDate(commandLine.getArgs()[2]);
            } else {
                throw new ParseException("please specify all required options", 0);
            }

            if (commandLine.hasOption("C")) {
                populateAccountTransactionsFromCSV();
            } else if (commandLine.hasOption("Q")) {
                populateAccountTransactionsFromCSV();
            } else {
                throw new ParseException("unknown option " + commandLine.getOptions()[0].getOpt(), 0);
            }
        } catch (ParseException e) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.setWidth(120);
            helpFormatter.printHelp(
                    "[-C -Q] [inputCsvFile] [startDate] [endDate]",
                    options);
        } catch (Exception e) {
            LOG.error("Exception : ", e);
        } finally {
            persistBankingTransactions();
        }
    }

    public static void populateAccountTransactionsFromCSV() {

        String csvFile = getInputCsvFile();

        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";


        try {

            br = new BufferedReader(new FileReader(csvFile));
            int rowCount = 0;
            csvTransactions = new ArrayList<>();


            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] strings = line.split(cvsSplitBy);

                for (int i = 0; i < strings.length; i++) {
                    String entry = strings[i];
                    strings[i] = entry.replace("\"", "");
                }


                for (String entry : strings) {
                    System.out.print(entry + ", ");
                }

                System.out.println("\n" + rowCount++ + " " + strings[0]);

                populateBankingTransactionList(strings);
                //storeBalanceFields(strings);
            }


            LOG.info("csvTransactions.size() = " + csvTransactions.size());


        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }


    private static void populateBankingTransactionList(String[] strings) throws ParseException {

        BankingTransaction bankingTransaction;

        LocalDate startDate = LocalDate.parse(getStartTxnDate());
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        startCalendar.roll(Calendar.DAY_OF_MONTH, -1);
        LOG.info("start date " + startCalendar.getTime().toLocaleString());

        LocalDate endDate = LocalDate.parse(getEndTxnDate());
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        LOG.info("end date " + endCalendar.getTime().toLocaleString());


        if (strings[0].contains("DEBIT") || strings[0].contains("CHECK") || strings[0].contains("DEP") || strings[0].contains("XFER")) {
            //This is to skip over the first non transactional csv lines


            bankingTransaction = new BankingTransaction();

            //Set type
            bankingTransaction.setType(strings[0].replace("\"", ""));

            //Set Posted Date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            String dateString = strings[1]; //.replace("\"", "");

            //LOG.info("dateString = " + dateString);
            TemporalAccessor ta = formatter.parse(dateString);
            LocalDate postedDate = LocalDate.from(ta);
            Calendar postedCalendar = Calendar.getInstance();
            postedCalendar.setTime(Date.from(postedDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            //LOG.info("posted date " + postedCalendar.getTime().toLocaleString());
            bankingTransaction.setPostedDate(postedCalendar);

            //Set Transaction Amount
            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
            decimalFormat.setParseBigDecimal(true);
            BigDecimal bigDecimal = (BigDecimal) decimalFormat.parseObject(strings[2].replace("\"", ""));
            bankingTransaction.setAmount(bigDecimal);
            //Set Transaction Id
            bankingTransaction.setInstitutionTransactionId(strings[3].replace("\"", ""));
            //Set Check Number
            bankingTransaction.setCheckNumber(strings[4].replace("\"", ""));
            //Set Transaction Name
            bankingTransaction.setPayeeName(strings[5]);
            bankingTransaction.setMemo(strings[6].replace("\"", ""));

            if (bankingTransaction.getPostedDate().after(startCalendar) && bankingTransaction.getPostedDate().before(endCalendar)) {
                System.out.println("Transaction Name: " + strings[5]);
                csvTransactions.add(bankingTransaction);
            }
        }
    }

    private static void storeBalanceFields(String[] strings) throws ParseException {
        if (strings[0].contains("AVAILABLE BALANCE")) { //This is to work out the available amount of cash

            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
            decimalFormat.setParseBigDecimal(true);
            availableBalance = (BigDecimal) decimalFormat.parseObject(strings[1].replace("\"", ""));

        } else if (strings[0].contains("LEDGERBALANCE")) { //This is to work out the available amount of cash

            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
            decimalFormat.setParseBigDecimal(true);
            ledgerBalance = (BigDecimal) decimalFormat.parseObject(strings[1].replace("\"", ""));

        }
    }


    @Override
    public void run(String... args) throws Exception {
        main(args);
    }

    static void persistBankingTransactions() {
        LOG.info("persistBankingTransaction called");
        for (BankingTransaction bankingTransaction : csvTransactions) {
            LOG.info(bankingTransaction.toString());
        }
    }
}