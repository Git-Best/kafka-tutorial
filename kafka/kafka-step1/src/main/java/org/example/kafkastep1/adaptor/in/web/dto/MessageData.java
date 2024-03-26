package org.example.kafkastep1.adaptor.in.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageData {

    private String title;
    private String contents;
    private LocalDateTime time;
}
