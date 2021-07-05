package com.packaging.solution.processor;

import com.packaging.solution.model.Case;
import com.packaging.solution.model.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PackagingProcessor {
    public void parseItems(File file) throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        while ((line = br.readLine()) != null) {
            if(isBlank(line)) {
                continue;
            }
            else {
                Case aCase = testCase(line);
                System.out.println(aCase);
                String result = new SolutionAnalyzer().getSolution(aCase);
                System.out.println("Output : " + result);
            }
        }
    }



    private boolean isBlank(String line) {
        return line.trim().length() == 0;
    }

    private Case testCase(String line) throws ParseException {
        String[] tokens = line.split(":");
        String[] itemTokens = tokens[1].trim().split("\\s+");

        Case aCase = new Case();
        aCase.setTotalWeight(Double.parseDouble(tokens[0].trim()));
        aCase.setItems(items(itemTokens));

        return aCase;
    }

    private List<Item> items(String[] items) throws ParseException {
        List<Item> itemList = new ArrayList<>();
        for(String item : items) {
            String text = item.trim().substring(1, item.length()-1);
            String[] token = text.split(",");
            Item itemObj = new Item();
            itemObj.setNumber(Integer.parseInt(token[0].trim()));
            itemObj.setWeight(Double.parseDouble(token[1].trim()));
            itemObj.setCost(getDouble(token[2].trim()));
            itemList.add(itemObj);
        }
        return itemList;
    }

    private double getDouble(String currencyInput) { // Accept number in currency input
        int cnt = 0;
        for(char c : currencyInput.toCharArray()) {
            if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5'
                    || c == '6' || c == '7' || c == '8' || c == '9') {
                break;
            }
            cnt++;
        }
        return Double.parseDouble(currencyInput.substring(cnt));
    }
}
