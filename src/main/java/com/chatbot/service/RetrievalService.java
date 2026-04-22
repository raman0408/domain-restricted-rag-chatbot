package com.chatbot.service;

import com.chatbot.model.QA;
import java.util.ArrayList;
import java.util.List;

public class RetrievalService {
    public List<QA> retrieval(List<QA> data, String query){
        List<QA> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();

        for(QA qa : data){
            String question = qa.getQuestion().toLowerCase();
            if (question.contains(lowerQuery) || lowerQuery.contains(question)){
                results.add(qa);
                continue;
            }
            for(String word : lowerQuery.split(" ")){
                if(question.contains(word)){
                    results.add(qa);
                    break;
                }
            }
        }
        if(results.isEmpty()){
            return data.subList(0, Math.min(3,data.size()));
        }
        return results.subList(0, Math.min(3, results.size()));
    }
}
