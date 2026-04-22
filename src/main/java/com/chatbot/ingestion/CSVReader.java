package com.chatbot.ingestion;

import com.chatbot.model.KBData;
import com.chatbot.model.QA;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static KBData load(){
        List<QA> data = new ArrayList<>();
        String systemPrompt = "";

        try{
            Reader in = new InputStreamReader(
                CSVReader.class.getClassLoader().getResourceAsStream("knowledgebase.csv")
            );
            
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
            .withHeader()
            .withSkipHeaderRecord()
            .parse(in);
            for(CSVRecord record:records){
                String category = record.get("CATEGORY");
                String subCategory = record.get("SUB_CATEGORY");
                String question = record.get("QUESTION");
                String answer = record.get("ANSWER");

                if("META".equalsIgnoreCase(category) && "SYSTEM_PROMPT".equalsIgnoreCase(subCategory)){
                    systemPrompt = answer;
                }
                else{
                    data.add(new QA(question, answer));
                }
            }
        } catch (Exception e){
            throw new RuntimeException("Error reading CSV", e);
        }
        return new KBData(systemPrompt, data);
    }
}
