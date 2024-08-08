package com.ntousoselab.karate.combination;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.Arrays;

public class GptOptimization {
    private static final String API_KEY = "填入API_KEY";

    public static String generateNewExamples(String featureLines) {
        Duration timeout = Duration.ofSeconds(60);
        OpenAiService service = new OpenAiService(API_KEY, timeout);
        ChatMessage systemMessage = new ChatMessage("system", "規則:你是用來幫助開發者優化Karate測試案例的系統，你必須讀取Karate Gherkin格式的測試案例，並確保以下事情。1.在原Examples表中補充參數，用來提高測試覆蓋率。2.不可以增加Scenario Outline(比如優化前只有3個Scenario Outline，那優化後也是一樣只能有3個Scenario Outline)。3.Scenario Outline中的Method是GET的話，Status code須放在Examples表中，並且Status code的值須考慮到如果是補充的參數，因為補充的參數可能不在SUT的資料庫中，但前面的Scenario如果有做POST除外。4.請直接給我Karate Gherkin，不要多餘的對話。");
        ChatMessage userMessage = new ChatMessage("user", "讀取以下Karate Gherkin：" + featureLines);
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model("gpt-4o")
                .messages(Arrays.asList(systemMessage, userMessage))
                .maxTokens(4096)
                .temperature(0.0)
                .build();
        ChatCompletionResult result = service.createChatCompletion(completionRequest);
        String generatedText = result.getChoices().get(0).getMessage().getContent();
        System.out.println(generatedText);
        generatedText = generatedText.replaceAll("```gherkin\\n", "").replaceAll("```", "");
        return generatedText.trim();
    }
}