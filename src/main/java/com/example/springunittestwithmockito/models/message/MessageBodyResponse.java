package com.example.springunittestwithmockito.models.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MessageBodyResponse {
    private Long id;
    private String message;
    private int rating;
}
