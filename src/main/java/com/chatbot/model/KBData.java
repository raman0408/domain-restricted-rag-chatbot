package com.chatbot.model;
import java.util.List;

public class KBData {
    private final String systemPrompt;
    private final List<QA> data;

    public KBData(String systemPrompt, List<QA> data){
        this.systemPrompt = systemPrompt;
        this.data = data;
    }
    public String getSystemPrompt(){
        return systemPrompt;
    }
    public List<QA> getData(){
        return data;
    }
}
