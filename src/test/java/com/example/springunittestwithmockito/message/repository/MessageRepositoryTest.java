package com.example.springunittestwithmockito.message.repository;

import com.example.springunittestwithmockito.entities.MessageEntity;
import com.example.springunittestwithmockito.models.message.MessageBodyRequest;
import com.example.springunittestwithmockito.repositories.database.message.dao.MessageDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ActiveProfiles("test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@SpringBootTest
public class MessageRepositoryTest {
    @Autowired
    private MessageDao messageDao;

    @BeforeEach
    public void init() {
        MessageBodyRequest messageBodyRequest1 = new MessageBodyRequest("very good 1", 5);
        this.messageDao.save(messageBodyRequest1);
        MessageBodyRequest messageBodyRequest2 = new MessageBodyRequest("very good 2", 2);
        this.messageDao.save(messageBodyRequest2);
        MessageBodyRequest messageBodyRequest3 = new MessageBodyRequest("very good 3", 3);
        this.messageDao.save(messageBodyRequest3);
    }

    @Test
    public void save() {
        MessageBodyRequest messageBodyRequest = new MessageBodyRequest("very good", 5);
        MessageEntity messageEntity = this.messageDao.save(messageBodyRequest);
        Assertions.assertEquals(messageBodyRequest.getMessage(), messageEntity.getMessage());
        Assertions.assertEquals(messageBodyRequest.getRating() ,messageEntity.getRating());
    }

    @Test
    public void getAll() {
        List<MessageEntity> messageEntities = this.messageDao.getAll();
        Assertions.assertEquals(3, messageEntities.size());
    }

    @Test
    public void getById() {
        MessageEntity messageEntity = this.messageDao.getById(1L);
        Assertions.assertEquals("very good 1", messageEntity.getMessage());
        Assertions.assertEquals(5, messageEntity.getRating());
    }

    @Test
    public void update() {
        MessageBodyRequest messageBodyRequest = new MessageBodyRequest("very good", 1);
        MessageEntity messageEntity = this.messageDao.update(1L, messageBodyRequest);
        Assertions.assertEquals(messageBodyRequest.getMessage(), messageEntity.getMessage());
        Assertions.assertEquals(messageBodyRequest.getRating(), messageEntity.getRating());
    }

    @Test
    public void delete() {
        this.messageDao.delete(1L);
        List<MessageEntity> messageEntities = this.messageDao.getAll();
        Assertions.assertEquals(2, messageEntities.size());
    }
}
