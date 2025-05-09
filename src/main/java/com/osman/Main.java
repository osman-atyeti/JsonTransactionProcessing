package com.osman;


import com.osman.model.Transaction;
import com.osman.process.TransactionProcess;

public class Main {
    public static void main(String[] args) {
        String transactionFile = "src/main/resources/transactions_preprocessing.json";
        String discountFile = "src/main/resources/discount_config.json";

        try {
            TransactionProcess transactionProcess=new TransactionProcess();
            transactionProcess.processTransaction(transactionFile, discountFile);
            System.out.println("Transaction processing completed successfully");
        } catch (Exception e) {
            System.out.println("Unable to process transactions: " + e.getMessage());
        }
    }
}