package com.chatbot.service;

import com.chatbot.model.QA;
import java.util.*;

public class VectorStoreService {
    private final List<QA> data = new ArrayList<>();
    private final List<List<Float>> vectors = new ArrayList<>();
    private final EmbeddingService embeddingService = new EmbeddingService();

    public void addAll(List<QA> qaList){
        for(QA qa : qaList){
            data.add(qa);
            vectors.add(embeddingService.embed(qa.getQuestion()));
        }
    }

    public List<QA> search(String query, int topK){
        List<Float> queryVec = embeddingService.embed(query);
        PriorityQueue<Map.Entry<QA, Double>> pq =
            new PriorityQueue<>(Comparator.comparingDouble((Map.Entry<QA, Double> e) -> e.getValue()).reversed());

        for (int i = 0; i < data.size(); i++) {
            double sim = cosine(queryVec, vectors.get(i));
            pq.add(new AbstractMap.SimpleEntry<>(data.get(i), sim));
        }

        List<QA> result = new ArrayList<>();
        for(int i = 0; i < topK && !pq.isEmpty(); i++){
            result.add(pq.poll().getKey());
        }
        return result;
    }
    private double cosine(List<Float> a, List<Float> b){
        double dot = 0, normA = 0, normB = 0;

        for(int i = 0; i < a.size(); i++){
            dot += a.get(i) * b.get(i);
            normA += a.get(i) * b.get(i);
            normB += a.get(i) * b.get(i);
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
