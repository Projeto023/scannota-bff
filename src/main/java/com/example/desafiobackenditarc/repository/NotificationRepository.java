package com.example.desafiobackenditarc.repository;

import com.example.desafiobackenditarc.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    Notification findFirstByOrderByIdDesc();
}
