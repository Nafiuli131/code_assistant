package com.powerledger.codeassistant.service;

import com.powerledger.codeassistant.dto.ChatHistory;
import com.powerledger.codeassistant.dto.ChatHistoryResponse;
import com.powerledger.codeassistant.dto.ChatRequest;
import com.powerledger.codeassistant.model.ChatMessage;
import com.powerledger.codeassistant.model.User;
import com.powerledger.codeassistant.repository.ChatRepository;
import com.powerledger.codeassistant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ChatServiceImpl implements ChatService {

    @Value("${together.api.key}")
    private String apiKey;

    @Value("${together.api.url}")
    private String apiUrl;

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ChatMessage process(ChatRequest req) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders h = new HttpHeaders();
        h.setBearerAuth(apiKey);
        h.setContentType(MediaType.APPLICATION_JSON);

        List<ChatMessage> history = chatRepository.findChatMessagesByChatNameAndUserId(req.getChatName(), req.getUserId());

        List<Map<String, Object>> messages = new ArrayList<>();
        for (ChatMessage chat : history) {
            List<Map<String, Object>> content = new ArrayList<>();
            content.add(Map.of("type", "text", "text", chat.getText()));
            if (chat.getImageUrl() != null) {
                content.add(Map.of("type", "image_url", "image_url", Map.of("url", chat.getImageUrl())));
            }
            messages.add(Map.of("role", chat.getRole(), "content", content));
        }

        // Append the current user message
        List<Map<String, Object>> currentContent = new ArrayList<>();
        currentContent.add(Map.of("type", "text", "text", req.getPrompt()));
        if (req.getImageUrl() != null) {
            currentContent.add(Map.of("type", "image_url", "image_url", Map.of("url", req.getImageUrl())));
        }
        messages.add(Map.of("role", "user", "content", currentContent));

        Map<String, Object> body = Map.of(
                "model", "meta-llama/Llama-Vision-Free",
                "messages", messages
        );

        Map<?, ?> resp = rt.postForObject(apiUrl, new HttpEntity<>(body, h), Map.class);

        String aiResp = ((Map<?, ?>) ((Map<?, ?>) ((List<?>) resp.get("choices")).get(0)).get("message")).get("content").toString();

        // Save user message
        ChatMessage userMsg = new ChatMessage();
        userMsg.setRole("user");
        userMsg.setText(req.getPrompt());
        userMsg.setImageUrl(req.getImageUrl());
        User user = userRepository.findById(req.getUserId()).orElse(null);
        if (user != null) {
            userMsg.setUser(user);
        }
        userMsg.setChatName(req.getChatName());
        chatRepository.save(userMsg);

        // Save assistant response
        ChatMessage aiMsg = new ChatMessage();
        aiMsg.setRole("assistant");
        aiMsg.setText(aiResp);
        if (user != null) {
            aiMsg.setUser(user);
        }
        aiMsg.setChatName(req.getChatName());
        chatRepository.save(aiMsg);

        return aiMsg;
    }

    @Override
    public List<ChatHistoryResponse> history(ChatHistory chatHistory) {
        List<ChatMessage> allByChatNameAndUserId = chatRepository.findAllByChatNameAndUserId(chatHistory.getChatName(), chatHistory.getUserId());
        List<ChatHistoryResponse> history = new ArrayList<>();
        for (ChatMessage chatMessage : allByChatNameAndUserId) {
            ChatHistoryResponse chatHistoryResponse = new ChatHistoryResponse();
            chatHistoryResponse.setChatName(chatMessage.getChatName());
            chatHistoryResponse.setChatText(chatMessage.getText());
            chatHistoryResponse.setRole(chatMessage.getRole());
            history.add(chatHistoryResponse);
        }
        return history;
    }

    @Override
    public List<ChatHistoryResponse> userAllChatHistory(String userId) {
        List<ChatMessage> allByChatNameAndUserId = chatRepository.findAllChatByUserId(userId);
        List<ChatHistoryResponse> history = new ArrayList<>();
        for (ChatMessage chatMessage : allByChatNameAndUserId) {
            ChatHistoryResponse chatHistoryResponse = new ChatHistoryResponse();
            chatHistoryResponse.setChatName(chatMessage.getChatName());
            chatHistoryResponse.setChatText(chatMessage.getText());
            chatHistoryResponse.setRole(chatMessage.getRole());
            history.add(chatHistoryResponse);
        }
        return history;
    }

}
