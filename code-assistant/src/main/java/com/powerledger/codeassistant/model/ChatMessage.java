package com.powerledger.codeassistant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue
    private Long id;
    private String role; // "user" or "assistant"
    private String chatName;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Lob
    private String text;
    private String imageUrl; // optional
    private LocalDateTime timestamp = LocalDateTime.now();
}
