package com.ntousoselab.karate.combination;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

public class CombinationsGenerator {
    public static List<Map<String, String>> generateCombinations(Map<String, List<String>> examples) {
        List<Map<String, String>> combinations = new ArrayList<>();
        List<Set<String>> sets = Lists.transform(new ArrayList<>(examples.values()), HashSet::new);
        Set<List<String>> cartesianProduct = Sets.cartesianProduct(sets);
        System.out.println("經過解析的Examples Data Tables:");
        System.out.println(examples+"\n");
        System.out.println("取出values，並建立集合:");
        System.out.println(sets+"\n");
        for (List<String> product : cartesianProduct) {
            Map<String, String> combination = new LinkedHashMap<>();
            for (int i = 0; i < examples.keySet().size(); i++) {
                String key = examples.keySet().toArray(new String[0])[i];
                combination.put(key, product.get(i));
            }
            combinations.add(combination);
        }
        System.out.println("笛卡兒積計算集合:");
        System.out.println(cartesianProduct+"\n");
        System.out.println("將計算完成的values集合重新分配到keys中:");
        System.out.println(combinations+"\n");
        System.out.println("將封裝成新的Examples Data Tables。\n");
        return combinations;
    }
}

