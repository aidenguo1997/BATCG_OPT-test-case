package com.ntousoselab.karate.combination;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "src/main/resources/original.feature";  // 請替換為你的輸入文件路徑
        String outputFilePath = "src/main/resources/output.feature";  // 請替換為你想輸出的文件路徑
        String optimizationFilePath = "src/main/resources/optimization.feature";

        // 讀取 Feature 文件內容
        String content = FeatureFileReader.readFeatureFile(inputFilePath);
        System.out.println("Reading feature file content:");
        System.out.println(content);

        // 解析 Scenario Outline 和 Examples
        List<String> scenarios = FeatureFileParser.parseFeatureFile(content);
        System.out.println("Parsed scenarios:");
        scenarios.forEach(System.out::println);

        List<String> newExamples = scenarios.stream().map(scenario -> {
            String exampleText = scenario.split("Examples:\\n", 2)[1];
            Map<String, List<String>> examples = ExamplesParser.parseExamples(exampleText);
            List<Map<String, String>> combinations = CombinationsGenerator.generateCombinations(examples);
            return ExamplesTableGenerator.generateExampleTable(examples.keySet().stream().toList(), combinations);
        }).collect(Collectors.toList());

        // 更新 Feature 文件內容
        String updatedContent = FeatureFileUpdater.updateFeatureFile(content, scenarios, newExamples);
        System.out.println("Updated feature file content:");
        System.out.println(updatedContent);
        // 寫入新的 Feature 文件
        FeatureFileWriter.writeFeatureFile(outputFilePath, updatedContent);
        String gptOptimization = GptOptimization.generateNewExamples(updatedContent);
        FeatureFileWriter.writeFeatureFile(optimizationFilePath, gptOptimization);
    }
}
