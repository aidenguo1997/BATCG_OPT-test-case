package com.ntousoselab.karate.combination;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class FeatureFileParser {
    private static final String SCENARIO_OUTLINE_PATTERN = "(Scenario Outline:.*?Examples:\\n(\\s*\\|.*?\\|.*?(?:\\n|$))+)";

    public static List<String> parseFeatureFile(String content) {
        Pattern pattern = Pattern.compile(SCENARIO_OUTLINE_PATTERN, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);
        List<String> scenarios = new ArrayList<>();

        while (matcher.find()) {
            scenarios.add(matcher.group(1));
        }
        return scenarios;
    }
}
