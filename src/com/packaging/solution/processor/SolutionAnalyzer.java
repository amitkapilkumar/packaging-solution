package com.packaging.solution.processor;

import com.packaging.solution.model.Case;
import com.packaging.solution.model.Combination;
import com.packaging.solution.model.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class SolutionAnalyzer {
    public String getSolution(Case aCase) {
        if(aCase.getTotalWeight() > 100) { // As per criteria, Maximum weight of the package should 100 or less
            return "-";
        }

        Map<Integer, Item> includedItems = new HashMap<>();
        for(Item item : aCase.getItems()) {
            if((item.getCost() <= 100 && item.getWeight() <= 100 && item.getWeight() <= aCase.getTotalWeight())) {
                includedItems.put(item.getNumber(), item);
            }
        }

        int[] array = includedItems.keySet().stream().mapToInt(Number::intValue).toArray();
        List<String> combinations = new ArrayList<>();
        int l = array.length-1;
        while (l > 0) {
            populateCombinations(array, array.length, l, combinations);
            l--;
        }

        String result = "-";
        Combination buffer = null;

        for(String combinationStr : combinations) {
            Combination combination = isValid(combinationStr, includedItems, aCase.getTotalWeight());
            if(combination.isValid()) {
                if(buffer == null) {
                    buffer = combination;
                }
                else {
                    if(combination.getTotalCost() > buffer.getTotalCost()) {
                        buffer = combination;
                        result = combinationStr;
                    }
                }
            }
        }

        return result;
    }

    private Combination isValid(String combinationStr, Map<Integer, Item> map, double maxWeight) {
        int[] array = Stream.of(combinationStr.substring(1, combinationStr.length()-1).trim().split(",\\s+")).mapToInt(Integer::parseInt).toArray();
        Combination combination = new Combination();
        if(array.length > 15) {
            return combination; // maximum 15 items in package
        }

        double weight = 0;
        double cost = 0;
        for(int a : array) {
            weight += map.get(a).getWeight();
            cost += map.get(a).getCost();
        }

        if(weight > maxWeight) {
            return combination;
        }

        combination.setValid(true);
        combination.setTotalWeight(weight);
        combination.setTotalCost(cost);
        return combination;
    }

    private void getCombinations(int arr[], int data[], int start, int end, int index, int r, List<String> result) {
        if (index == r) {
            result.add(Arrays.toString(data));
            return;
        }

        for (int i=start; i<=end && end-i+1 >= r-index; i++) {
            data[index] = arr[i];
            getCombinations(arr, data, i+1, end, index+1, r, result);
        }
    }


    private void populateCombinations(int arr[], int n, int r, List<String> result) {
        int[] initValues = new int[r];
        getCombinations(arr, initValues, 0, n-1, 0, r, result);
    }
}
