package com.example.springunittestwithmockito.message.controller;

import com.example.springunittestwithmockito.controllers.MessageController;
import com.example.springunittestwithmockito.entities.MessageEntity;
import com.example.springunittestwithmockito.models.message.MessageBodyRequest;
import com.example.springunittestwithmockito.models.message.MessageBodyResponse;
import com.example.springunittestwithmockito.services.message.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
@ActiveProfiles("test")
public class MessageControllerTest {

    protected static final List<MessageEntity> messageEntityList = new ArrayList<>();
    protected static final MessageEntity messageEntity = new MessageEntity(1L, "ดีมาก", 1);
    protected static final MessageBodyRequest messageRequest = new MessageBodyRequest("ดีมาก", 1);
    protected static final MessageBodyResponse messageResponse = new MessageBodyResponse(1L, "ดีมาก", 1);
    @MockBean
    private MessageService messageService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public static void init() {
        messageEntityList.add(new MessageEntity(1L, "ดีมาก", 1));
        messageEntityList.add(new MessageEntity(2L, "ดีมาก", 2));
        messageEntityList.add(new MessageEntity(3L, "ดีมาก", 3));
        messageEntityList.add(new MessageEntity(4L, "ดีมาก", 4));
        messageEntityList.add(new MessageEntity(5L, "ดีมาก", 5));
    }

    @Test
    public void getAll_success() throws Exception {
        Mockito.when(this.messageService.getAll()).thenReturn(messageEntityList);
        this.mockMvc.perform(get("/api/v1/messages")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getAll_error() throws Exception {
        Mockito.when(this.messageService.getAll()).thenThrow(RuntimeException.class);
        this.mockMvc.perform(get("/api/v1/messages")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("500"));
    }

    @Test
    public void getById_success() throws Exception {
        Mockito.when(this.messageService.getById(1L)).thenReturn(messageResponse);
        this.mockMvc.perform(get("/api/v1/message/{id}", "1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getById_error_notFound() throws Exception {
        Mockito.when(this.messageService.getById(1L)).thenThrow(NoSuchElementException.class);
        this.mockMvc.perform(get("/api/v1/message/{id}", "1")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("404"));
    }

    @Test
    public void getById_error_otherException() throws Exception {
        Mockito.when(this.messageService.getById(1L)).thenThrow(RuntimeException.class);
        this.mockMvc.perform(get("/api/v1/message/{id}", "1")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("500"));
    }

    @Test
    public void save_success() throws Exception {
        Mockito.when(this.messageService.save(messageRequest)).thenReturn(messageResponse);
        this.mockMvc.perform(post("/api/v1/message").content(this.objectMapper.writeValueAsBytes(messageRequest))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void save_error() throws Exception {
        Mockito.when(this.messageService.save(messageRequest)).thenThrow(RuntimeException.class);
        this.mockMvc.perform(post("/api/v1/message").content(this.objectMapper.writeValueAsBytes(messageRequest))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("500"));
    }

    @Test
    public void update_success() throws Exception {
        Mockito.when(this.messageService.update(1L, messageRequest)).thenReturn(messageResponse);
        this.mockMvc.perform(put("/api/v1/message/{id}", "1").content(this.objectMapper.writeValueAsBytes(messageRequest))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void update_error_notFound() throws Exception {
        Mockito.when(this.messageService.update(1L, messageRequest)).thenThrow(NoSuchElementException.class);
        this.mockMvc.perform(put("/api/v1/message/{id}", "1").content(this.objectMapper.writeValueAsBytes(messageRequest))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("404"));
    }

    @Test
    public void update_error_other() throws Exception {
        Mockito.when(this.messageService.update(1L, messageRequest)).thenThrow(RuntimeException.class);
        this.mockMvc.perform(put("/api/v1/message/{id}", "1").content(this.objectMapper.writeValueAsBytes(messageRequest))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("500"));
    }

    @Test
    public void delete_success() throws Exception {
        Mockito.doNothing();
        this.mockMvc.perform(delete("/api/v1/message/{id}", "1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void delete_error_notFound() throws Exception {
        Mockito.doThrow(NoSuchElementException.class);
        this.mockMvc.perform(delete("/api/v1/message/{id}", "1")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("500"));
    }

    @Test
    public void delete_error() throws Exception {
        Mockito.doThrow(RuntimeException.class);
        this.mockMvc.perform(delete("/api/v1/message/{id}", "1")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("500"));
    }
}
