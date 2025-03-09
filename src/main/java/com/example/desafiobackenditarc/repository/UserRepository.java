package com.example.desafiobackenditarc.repository;

import com.example.desafiobackenditarc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u where u.cityName = :cityName and u.allowNotifications = true")
    List<User> findByCityName(String cityName);
}
