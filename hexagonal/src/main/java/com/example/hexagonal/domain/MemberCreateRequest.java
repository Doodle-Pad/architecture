package com.example.hexagonal.domain;

/**
 * packageName    : com.example.hexagonal.domain
 * fileName       : MemberCreateRequest
 * author         : Yeong-Huns
 * date           : 26. 9. 12.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 9. 12.        Yeong-Huns       최초 생성
 */
public record MemberCreateRequest(String email, String nickname, String password) {}
