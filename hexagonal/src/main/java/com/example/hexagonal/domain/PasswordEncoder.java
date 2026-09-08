package com.example.hexagonal.domain;

/**
 * packageName    : com.example.hexagonal.domain
 * fileName       : PasswordEncoder
 * author         : Yeong-Huns
 * date           : 26. 9. 8.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 9. 8.        Yeong-Huns       최초 생성
 */
public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String password, String passwordHash);
}
