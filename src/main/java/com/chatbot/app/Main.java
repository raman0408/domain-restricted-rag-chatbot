package com.chatbot.app;

import com.chatbot.ingestion.CSVReader;
import com.chatbot.model.KBData;
import com.chatbot.service.ChatService;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        KBData kb = CSVReader.load();
        ChatService chatService = new ChatService(kb);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Chatbot ready! Type 'exit' to quit.");

        while (true){
            System.out.print("You: ");
            String input = scanner.nextLine();

            if(input.equalsIgnoreCase("exit")){
                break;
            }
            String response = chatService.ask(input);
            System.out.println("Bot: "+response);
        }
        scanner.close();
    }
}
