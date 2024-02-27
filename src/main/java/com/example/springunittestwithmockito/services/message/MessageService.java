package com.example.springunittestwithmockito.services.message;

import com.example.springunittestwithmockito.entities.MessageEntity;
import com.example.springunittestwithmockito.models.message.MessageBodyRequest;
import com.example.springunittestwithmockito.models.message.MessageBodyResponse;

import java.util.List;

public interface MessageService {
    List<MessageEntity> getAll();

    MessageBodyResponse getById(Long id);

    MessageBodyResponse save(MessageBodyRequest messageBodyRequest);

    MessageBodyResponse update(Long id, MessageBodyRequest messageBodyRequest);

    void delete(Long id);
}
