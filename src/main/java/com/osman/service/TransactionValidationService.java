
package com.osman.service;

import com.osman.model.TransactionItem;
import com.osman.model.ItemsItem;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TransactionValidationService {

    public Map<String, List<TransactionItem>> classifyTransactions(List<TransactionItem> transactions, Map<String, Double> discountMap) {
        List<TransactionItem> validTransactions = new ArrayList<>();
        List<TransactionItem> invalidTransactions = new ArrayList<>();
        Set<String> globalItemUuids = new HashSet<>();

        List<TransactionItem> updatedTransactions = calculateTransactionFields(transactions, discountMap);

        for (TransactionItem transactionItem : updatedTransactions) {
            boolean isValid = isTransactionValid(transactionItem, globalItemUuids);
            if (isValid) {
                validTransactions.add(transactionItem);
            } else {
                invalidTransactions.add(transactionItem);
            }
        }

        Map<String, List<TransactionItem>> result = new HashMap<>();
        result.put("all", updatedTransactions);
        result.put("valid", validTransactions);
        result.put("invalid", invalidTransactions);
        return result;
    }

    public List<TransactionItem> calculateTransactionFields(List<TransactionItem> transactions, Map<String, Double> discountMap) {
        for (TransactionItem transactionItem : transactions) {
            double totalBeforeDiscount = 0.0;
            double totalDiscountApplied = 0.0;
            for (ItemsItem item : transactionItem.getItems()) {
                double itemSubtotal = item.getItemSubtotal();
                String category = item.getCategory();
                double discountRate = discountMap.getOrDefault(category, 0.0);
                double discount = itemSubtotal * discountRate;
                totalBeforeDiscount += itemSubtotal;
                totalDiscountApplied += discount;
            }

            double finalAmount = totalBeforeDiscount - totalDiscountApplied;
            transactionItem.setTotalBeforeDiscount((int) Math.round(totalBeforeDiscount));
            transactionItem.setTotalDiscountApplied((int) Math.round(totalDiscountApplied));
            transactionItem.setFinalAmountPayable((int) Math.round(finalAmount));
        }
        return transactions;
    }

    public boolean isTransactionValid(TransactionItem transactionItem, Set<String> seenItems) {
        boolean isValid = true;
        for (ItemsItem item : transactionItem.getItems()) {
            if (!seenItems.add(item.getItemUuid())) {
                return false;
            }
        }

        double finalAmount = transactionItem.getFinalAmountPayable();
        double actualPaid = transactionItem.getPayment().getAmount();
        if (actualPaid < finalAmount) {
            return false;
        }

        ZonedDateTime orderTime = ZonedDateTime.parse(transactionItem.getTimestamp());
        LocalDate orderDate = orderTime.toLocalDate();
        LocalDate deliveryDate = LocalDate.parse(transactionItem.getShipping().getDeliveryEstimate());
        long deliveryDays = ChronoUnit.DAYS.between(orderDate, deliveryDate);
        if (deliveryDays > 7) {
            return false;
        }
        return isValid;
    }

}
