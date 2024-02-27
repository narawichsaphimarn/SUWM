package com.example.springunittestwithmockito.repositories.database.message.dao;

import com.example.springunittestwithmockito.entities.MessageEntity;
import com.example.springunittestwithmockito.models.message.MessageBodyRequest;
import com.example.springunittestwithmockito.repositories.database.message.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MessageDao {
    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public List<MessageEntity> getAll() {
        return this.messageRepository.findAll();
    }

    @Transactional
    public MessageEntity getById(Long id) {
        Optional<MessageEntity> optionalMessageEntity = this.messageRepository.findById(id);
        return optionalMessageEntity.orElseThrow();
    }

    @Transactional
    public MessageEntity save(MessageBodyRequest messageBodyRequest) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setRating(messageBodyRequest.getRating());
        messageEntity.setMessage(messageBodyRequest.getMessage());
        return this.messageRepository.save(messageEntity);
    }

    @Transactional
    public MessageEntity update(Long id, MessageBodyRequest messageBodyRequest) {
        Optional<MessageEntity> optionalMessageEntity = this.messageRepository.findById(id);
        MessageEntity messageEntity = optionalMessageEntity.orElseThrow();
        messageEntity.setMessage(messageBodyRequest.getMessage());
        messageEntity.setRating(messageBodyRequest.getRating());
        return this.messageRepository.save(messageEntity);
    }

    @Transactional
    public void delete(Long id) {
        Optional<MessageEntity> optionalMessageEntity = this.messageRepository.findById(id);
        messageRepository.delete(optionalMessageEntity.orElseThrow());
    }
}
