package com.ntousoselab.karate.combination;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeatureFileUpdater {
    private static final String EXAMPLES_PATTERN = "Examples:\\n(\\s*\\|.*?\\|.*?(?:\\n|$))+";

    public static String updateFeatureFile(String content, List<String> scenarios, List<String> newExamples) {
        String updatedContent = content;
        for (int i = 0; i < scenarios.size(); i++) {
            String scenario = scenarios.get(i);
            String newExampleTable = newExamples.get(i);
            Matcher matcher = Pattern.compile(EXAMPLES_PATTERN, Pattern.DOTALL).matcher(scenario);
            while (matcher.find()) {
                String oldExampleSection = matcher.group(0);
                String newExampleSection = "Examples:\n" + newExampleTable + "\n";
                scenario = scenario.replace(oldExampleSection, newExampleSection);
            }
            updatedContent = updatedContent.replace(scenarios.get(i), scenario);
        }
        return updatedContent;
    }
}
