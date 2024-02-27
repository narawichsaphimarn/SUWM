package com.example.springunittestwithmockito.message.service;

import com.example.springunittestwithmockito.entities.MessageEntity;
import com.example.springunittestwithmockito.models.message.MessageBodyRequest;
import com.example.springunittestwithmockito.models.message.MessageBodyResponse;
import com.example.springunittestwithmockito.repositories.database.message.dao.MessageDao;
import com.example.springunittestwithmockito.services.message.impl.MessageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ExtendWith(SpringExtension.class)
public class MessageServiceTest {
    protected static final List<MessageEntity> messageEntityList = new ArrayList<>();
    protected static final MessageEntity messageEntity = new MessageEntity(1L, "ดีมาก", 1);
    protected static final MessageBodyRequest messageRequest = new MessageBodyRequest("ดีมาก", 1);
    @Spy
    @InjectMocks
    private MessageServiceImpl messageService;
    @Mock
    private MessageDao messageDao;

    @BeforeAll
    public static void init() {
        messageEntityList.add(new MessageEntity(1L, "ดีมาก", 1));
        messageEntityList.add(new MessageEntity(2L, "ดีมาก", 2));
        messageEntityList.add(new MessageEntity(3L, "ดีมาก", 3));
        messageEntityList.add(new MessageEntity(4L, "ดีมาก", 4));
        messageEntityList.add(new MessageEntity(5L, "ดีมาก", 5));
    }

    @Test
    public void test_getAll_success() {
        Mockito.when(messageDao.getAll()).thenReturn(messageEntityList);
        List<MessageEntity> messageEntities = this.messageService.getAll();
        Assertions.assertEquals(messageEntityList.size(), messageEntities.size());
    }

    @Test
    public void test_getById_success() {
        Mockito.when(messageDao.getById(1L)).thenReturn(messageEntity);
        MessageBodyResponse messageBodyResponse = this.messageService.getById(1L);
        Assertions.assertEquals(messageEntity.getId(), messageBodyResponse.getId());
        Assertions.assertEquals(messageEntity.getMessage(), messageBodyResponse.getMessage());
        Assertions.assertEquals(messageEntity.getRating(), messageBodyResponse.getRating());
    }

    @Test
    public void test_getById_error_noSuchElement() {
        Mockito.when(messageDao.getById(1L)).thenThrow(new NoSuchElementException());
        try {
            this.messageService.getById(1L);
        } catch (NoSuchElementException ex) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_success() {
        Mockito.when(messageDao.save(messageRequest)).thenReturn(messageEntity);
        MessageBodyResponse messageBodyResponse = this.messageService.save(messageRequest);
        Assertions.assertEquals(messageEntity.getId(), messageBodyResponse.getId());
        Assertions.assertEquals(messageEntity.getMessage(), messageBodyResponse.getMessage());
        Assertions.assertEquals(messageEntity.getRating(), messageBodyResponse.getRating());
    }

    @Test
    public void test_update_success() {
        Mockito.when(messageDao.update(1L, messageRequest)).thenReturn(messageEntity);
        MessageBodyResponse messageBodyResponse = this.messageService.update(1L, messageRequest);
        Assertions.assertEquals(messageEntity.getId(), messageBodyResponse.getId());
        Assertions.assertEquals(messageEntity.getMessage(), messageBodyResponse.getMessage());
        Assertions.assertEquals(messageEntity.getRating(), messageBodyResponse.getRating());
    }

    @Test
    public void test_update_error_noSuchElement() {
        Mockito.when(messageDao.update(1L, messageRequest)).thenThrow(NoSuchElementException.class);
        try {
            this.messageService.update(1L, messageRequest);
        } catch (NoSuchElementException ex) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_delete_success() {
        this.messageService.delete(1L);
        Assertions.assertTrue(true);
    }
}
