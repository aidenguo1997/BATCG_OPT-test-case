package com.ntousoselab.karate.combination;

import java.util.*;

public class ExamplesParser {
    public static Map<String, List<String>> parseExamples(String exampleText) {
        String[] lines = exampleText.trim().split("\\n");
        String[] headers = lines[0].split("\\|");
        headers = Arrays.stream(headers).map(String::trim).filter(h -> !h.isEmpty()).toArray(String[]::new);

        Map<String, List<String>> examples = new LinkedHashMap<>();
        for (String header : headers) {
            examples.put(header, new ArrayList<>());
        }

        for (int i = 1; i < lines.length; i++) {
            String[] values = lines[i].split("\\|");
            for (int j = 0; j < headers.length; j++) {
                examples.get(headers[j]).add(values[j + 1].trim());
            }
        }

        return examples;
    }
}
