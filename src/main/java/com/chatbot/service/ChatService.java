package com.chatbot.service;

import com.chatbot.model.KBData;
import com.chatbot.model.QA;
import dev.langchain4j.model.ollama.OllamaLanguageModel;
import dev.langchain4j.model.output.Response;

import java.time.Duration;
import java.util.List;

public class ChatService {
    private final OllamaLanguageModel model;
    private final KBData kbData;
    private final RetrievalService retrievalService = new RetrievalService();
    private final VectorStoreService vectorStore = new VectorStoreService();
    public ChatService(KBData kbData){
        this.kbData = kbData;
        this.model = OllamaLanguageModel.builder()
        .baseUrl("http://localhost:11434")
        .modelName("qwen3:1.7b")
        .temperature(0.2)
        .timeout(Duration.ofSeconds(60))
        .build();
        vectorStore.addAll(kbData.getData());
    }

    public String ask(String userInput){
        
        List<QA> relevant = vectorStore.search(userInput, 3);

        StringBuilder prompt = new StringBuilder();

        prompt.append(kbData.getSystemPrompt()).append("\n\n");
        for(QA qa : relevant){
            prompt.append("Q: ").append(qa.getQuestion()).append("\n");
            prompt.append("A: ").append(qa.getAnswer()).append("\n\n");
        }

        prompt.append("User: ").append(userInput).append("\n");
        prompt.append("Assistant: ");

        Response<String> response = model.generate(prompt.toString());
        return response.content()
        .replaceAll("(?s)<think>.*?</think>", "")
        .trim();

    }
}
