package com.powerledger.codeassistant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    private Long id;
    private String prompt;
    private String response;
    private String imageUrl;
    private LocalDateTime ts;
}
