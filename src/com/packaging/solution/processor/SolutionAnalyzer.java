package com.packaging.solution.processor;

import com.packaging.solution.model.Case;
import com.packaging.solution.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SolutionAnalyzer {
    public List<String> getSolution(Case aCase) {
        List<String> result = new ArrayList<>();
        if(aCase.getTotalWeight() > 100) { // As per criteria, Maximum weight of the package should 100 or less
            return result;
        }

        List<Item> includedItems = new ArrayList<>();
        for(Item item : aCase.getItems()) {
            if((item.getCost() <= 100 && item.getWeight() <= 100 && item.getWeight() <= aCase.getTotalWeight())) {
                includedItems.add(item);
            }
        }

        int MAX_NO_ITEMS = 15;

        for(int i=0; i < includedItems.size(); i++) {
            for(int j = Double.valueOf(aCase.getTotalWeight()).intValue(); j >= includedItems.get(i).getWeight(); j--) {

            }
        }

        return result;
    }
}
