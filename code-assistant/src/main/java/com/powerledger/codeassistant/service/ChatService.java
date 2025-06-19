package com.powerledger.codeassistant.service;

import com.powerledger.codeassistant.dto.ChatHistory;
import com.powerledger.codeassistant.dto.ChatHistoryResponse;
import com.powerledger.codeassistant.dto.ChatRequest;
import com.powerledger.codeassistant.dto.ChatResponse;
import com.powerledger.codeassistant.model.ChatMessage;

import java.util.List;

public interface ChatService {
     ChatMessage process(ChatRequest req);

     List<ChatHistoryResponse> history(ChatHistory chatHistory);

     List<ChatHistoryResponse> userAllChatHistory(String userId);
}
