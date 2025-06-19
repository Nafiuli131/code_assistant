package com.powerledger.codeassistant.repository;

import com.powerledger.codeassistant.dto.ChatHistory;
import com.powerledger.codeassistant.dto.ChatHistoryResponse;
import com.powerledger.codeassistant.model.ChatMessage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    @Query("select c from ChatMessage c where c.user.id=:userId and c.chatName=:chatName ORDER BY c.timestamp ASC ")
    List<ChatMessage> findChatMessagesByChatNameAndUserId(String chatName, Long userId);

    @Query("select ch from ChatMessage ch where ch.chatName=:chatName and ch.user.id=:userId ")
    List<ChatMessage> findAllByChatNameAndUserId(String chatName, String userId);

    @Query("select ch from ChatMessage ch where ch.user.id=:userId ")
    List<ChatMessage> findAllChatByUserId(String userId);
}
