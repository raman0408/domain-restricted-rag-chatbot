package com.chatbot.service;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;

import java.util.List;
import java.util.ArrayList;

public class EmbeddingService {
    private final EmbeddingModel model;

    public EmbeddingService(){
        this.model = OllamaEmbeddingModel.builder()
        .baseUrl("http://localhost:11434")
        .modelName("nomic-embed-text")
        .build();
    }
    public List<Float> embed(String text){
        float[] vector = model.embed(text).content().vector();

        List<Float> list = new ArrayList<>();
        for(float v : vector){
            list.add(v);
        }
        return list;
    }
}
