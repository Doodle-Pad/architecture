package com.example.hexagonal.domain;

import java.util.regex.Pattern;

/**
 * packageName    : com.example.hexagonal.domain
 * fileName       : Email
 * author         : Yeong-Huns
 * date           : 26. 9. 12.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 9. 12.        Yeong-Huns       최초 생성
 */
public record Email(String address) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

    public Email {
        if (!EMAIL_PATTERN.matcher(address).matches()) {
            throw new IllegalArgumentException("이메일 형식이 바르지 않습니다: " + address);
        }
    }
}
