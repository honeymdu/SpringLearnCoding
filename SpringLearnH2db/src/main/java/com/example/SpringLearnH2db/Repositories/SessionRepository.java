package com.example.SpringLearnH2db.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringLearnH2db.Entitys.Session;
import com.example.SpringLearnH2db.Entitys.User;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByUser(User user);

    Optional<Session> findByRefreshToken(String refreshToken);

}
