package com.example.springunittestwithmockito.models.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageBodyRequest {
    private String message;
    private int rating;
}
