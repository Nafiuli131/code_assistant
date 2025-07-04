package com.powerledger.codeassistant.controller;

import com.powerledger.codeassistant.dto.ChatHistory;
import com.powerledger.codeassistant.dto.ChatHistoryResponse;
import com.powerledger.codeassistant.dto.ChatRequest;
import com.powerledger.codeassistant.dto.ChatResponse;
import com.powerledger.codeassistant.model.ChatMessage;
import com.powerledger.codeassistant.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")  // allow any origin
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest req) {
        ChatMessage ai = chatService.process(req);
        return new ResponseEntity<>(new ChatResponse(ai.getId(), req.getPrompt(), ai.getText(), req.getImageUrl(), ai.getTimestamp()), HttpStatus.OK);
    }

    @PostMapping("/history")
    public ResponseEntity<List<ChatHistoryResponse>> history(@RequestBody ChatHistory chatHistory) {
        List<ChatHistoryResponse> chatResponses = chatService.history(chatHistory);
        return new ResponseEntity<>(chatResponses, HttpStatus.OK);
    }

    @PostMapping("/userAllChatHistory")
    public ResponseEntity<List<ChatHistoryResponse>> userAllChatHistory(@RequestBody ChatHistory chatHistory) {
        List<ChatHistoryResponse> chatResponses = chatService.userAllChatHistory(chatHistory.getUserId());
        return new ResponseEntity<>(chatResponses, HttpStatus.OK);
    }
}
