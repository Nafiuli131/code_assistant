package com.powerledger.codeassistant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private Long userId;
    private String chatName;
    private String prompt;
    private String imageUrl;
}
