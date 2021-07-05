package com.packaging.solution.processor;

import com.packaging.solution.model.Case;
import com.packaging.solution.model.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PackagingProcessor {
    public void parseItems(File file) throws IOException {
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

    private Case testCase(String line) {
        String[] tokens = line.split(":");
        String[] itemTokens = tokens[1].trim().split("\\s+");

        Case aCase = new Case();
        aCase.setTotalWeight(Double.parseDouble(tokens[0].trim()));
        aCase.setItems(items(itemTokens));

        return aCase;
    }

    private List<Item> items(String[] items) {
        List<Item> itemList = new ArrayList<>();
        for(String item : items) {
            String text = item.trim().substring(1, item.length()-1);
            String[] token = text.split(",");
            Item itemObj = new Item();
            itemObj.setNumber(Integer.parseInt(token[0].trim()));
            itemObj.setWeight(Double.parseDouble(token[1].trim()));
            itemObj.setCost(Double.parseDouble(token[2].trim().substring(1)));
            itemList.add(itemObj);
        }
        return itemList;
    }
}
