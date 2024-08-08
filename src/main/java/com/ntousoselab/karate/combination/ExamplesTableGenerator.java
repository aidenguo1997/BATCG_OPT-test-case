package com.ntousoselab.karate.combination;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExamplesTableGenerator {
    public static String generateExampleTable(List<String> headers, List<Map<String, String>> examples) {
        String headerRow = String.join(" | ", headers);
        List<String> rows = new ArrayList<>();
        rows.add(headerRow);

        for (Map<String, String> example : examples) {
            List<String> row = new ArrayList<>();
            for (String header : headers) {
                row.add(example.get(header));
            }
            rows.add(String.join(" | ", row));
        }

        return rows.stream().map(row -> "      | " + row + " |").collect(Collectors.joining("\n"));
    }
}
