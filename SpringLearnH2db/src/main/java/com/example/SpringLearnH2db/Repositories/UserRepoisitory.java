package com.example.SpringLearnH2db.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringLearnH2db.Entitys.User;
import java.util.Optional;


@Repository
public interface UserRepoisitory extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
