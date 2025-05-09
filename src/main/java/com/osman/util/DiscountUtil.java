package com.osman.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class DiscountUtil {

     static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String,Double> readDiscount(String filePath){
        try {
            return objectMapper.readValue(new File(filePath),Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DiscountUtil discountUtil = new DiscountUtil();
        Map<String, Double> discount = readDiscount("src/main/resources/discount_config.json");
        if (discount != null) {
            for (Map.Entry<String, Double> entry : discount.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("Failed to read discount.");
        }
    }

}
