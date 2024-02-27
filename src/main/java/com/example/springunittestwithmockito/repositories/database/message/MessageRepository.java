package com.example.springunittestwithmockito.repositories.database.message;

import com.example.springunittestwithmockito.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
