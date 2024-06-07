package com.project.task.config;

import com.project.task.dao.UserSessionDao;
import com.project.task.dto.UserSessionDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static boolean validateToken(String token, String userId, UserSessionDao userSessionDao) {
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setUserId(Long.parseLong(userId));
        userSessionDto.setToken(token);
        int session = userSessionDao.isValidToken(userSessionDto);
        return session >= 1;
    }
}
