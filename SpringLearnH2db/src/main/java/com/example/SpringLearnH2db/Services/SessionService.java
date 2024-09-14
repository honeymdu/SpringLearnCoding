package com.example.SpringLearnH2db.Services;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SpringLearnH2db.Entitys.Session;
import com.example.SpringLearnH2db.Entitys.User;
import com.example.SpringLearnH2db.Repositories.SessionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;

    public void GenerateNewSession(User user, String Refreshtoken) {
        List<Session> Usersessions = sessionRepository.findByUser(user);
        if (Usersessions.size() == SESSION_LIMIT) {
            Usersessions.sort(Comparator.comparing(Session::getLastUserAt));
            Session leastRecenteltyUsedSession = Usersessions.get(0);
            sessionRepository.delete(leastRecenteltyUsedSession);
        }
        Session newSession = Session.builder()
                .user(user)
                .refreshToken(Refreshtoken)
                .build();

        sessionRepository.save(newSession);

    }

    public void ValidateSession(String refreshToken) {
        sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Session not found for current Refresh Token"));

    }

}
