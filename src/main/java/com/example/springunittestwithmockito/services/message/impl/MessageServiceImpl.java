package com.example.springunittestwithmockito.services.message.impl;

import com.example.springunittestwithmockito.entities.MessageEntity;
import com.example.springunittestwithmockito.models.message.MessageBodyRequest;
import com.example.springunittestwithmockito.models.message.MessageBodyResponse;
import com.example.springunittestwithmockito.repositories.database.message.dao.MessageDao;
import com.example.springunittestwithmockito.services.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public List<MessageEntity> getAll() {
        return this.messageDao.getAll();
    }

    @Override
    public MessageBodyResponse getById(Long id) {
        MessageEntity messageEntity = this.messageDao.getById(id);
        return MessageBodyResponse.builder().id(messageEntity.getId()).message(messageEntity.getMessage()).rating(messageEntity.getRating()).build();
    }

    @Override
    public MessageBodyResponse save(MessageBodyRequest messageBodyRequest) {
        MessageEntity messageEntity = this.messageDao.save(messageBodyRequest);
        return MessageBodyResponse.builder().id(messageEntity.getId()).message(messageEntity.getMessage()).rating(messageEntity.getRating()).build();
    }

    @Override
    public MessageBodyResponse update(Long id, MessageBodyRequest messageBodyRequest) {
        MessageEntity messageEntity = this.messageDao.update(id, messageBodyRequest);
        return MessageBodyResponse.builder().id(messageEntity.getId()).message(messageEntity.getMessage()).rating(messageEntity.getRating()).build();
    }

    @Override
    public void delete(Long id) {
        this.messageDao.delete(id);
    }
}
