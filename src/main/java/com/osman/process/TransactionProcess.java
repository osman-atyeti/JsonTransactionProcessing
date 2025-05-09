package com.osman.process;

import com.osman.model.TransactionItem;
import com.osman.service.TransactionValidationService;
import com.osman.util.DiscountUtil;
import com.osman.util.JsonUtil;

import java.util.List;
import java.util.Map;

public class TransactionProcess {

    TransactionValidationService transactionValidationService ;

    public TransactionProcess() {
        transactionValidationService = new TransactionValidationService();
    }

    public void processTransaction(String transactionFile, String discountFile) {

        List<TransactionItem> transactions = JsonUtil.readTranactions(transactionFile);
        Map<String, Double> discountMap = DiscountUtil.readDiscount(discountFile);

        Map<String,List<TransactionItem>> classifiedTransactions = transactionValidationService.classifyTransactions(transactions, discountMap);

        JsonUtil.writeTransactions("transactions.json",classifiedTransactions.get("all"));
//        System.out.println(classifiedTransactions.get("all").size());

        JsonUtil.writeTransactions("valid.json",classifiedTransactions.get("valid"));
//        System.out.println(classifiedTransactions.get("valid").size());

        JsonUtil.writeTransactions("invalid.json",classifiedTransactions.get("invalid"));
//        System.out.println(classifiedTransactions.get("invalid").size());

    }
}
