package com.osman.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osman.model.Transaction;
import com.osman.model.TransactionItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class JsonUtil {

    public static List<TransactionItem> readTranactions(String filePath){
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<TransactionItem> transaction = mapper.readValue(new File(filePath), new TypeReference<>() {
            });
            return transaction;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeTransactions(String filePath, List<TransactionItem> transactions) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), transactions);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        JsonUtil jsonUtil = new JsonUtil();
        List<TransactionItem> transactions = readTranactions("src/main/resources/transactions_preprocessing.json");
        if (transactions != null) {
            for (TransactionItem transaction : transactions) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Failed to read transactions.");
        }
    }

}
