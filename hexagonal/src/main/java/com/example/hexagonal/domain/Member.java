package com.example.hexagonal.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.util.Objects;

import static org.springframework.util.Assert.state;

/**
 * packageName    : com.example.hexagonal.domain
 * fileName       : Member
 * author         : Yeong-Huns
 * date           : 26. 9. 6.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 9. 6.        Yeong-Huns       최초 생성
 */
@Getter
@ToString
public class Member {
    private String email;

    private String nickname;

    private String passwordHash;

    private MemberStatus status;

    public Member(String email, String nickname, String passwordHash) {
        this.email = Objects.requireNonNull(email);
        this.nickname = Objects.requireNonNull(nickname);
        this.passwordHash = Objects.requireNonNull(passwordHash);

        // 생성시 기본값: PENDING
        this.status = MemberStatus.PENDING;
    }

    public void activate() {
        // if (this.status != MemberStatus.PENDING) throw new IllegalStateException("PENDING 상태가 아닙니다");
        state(status == MemberStatus.PENDING, "PENDING 상태가 아닙니다");

        this.status = MemberStatus.ACTIVE;
    }

    public void deactivate() {
        state(status == MemberStatus.ACTIVE, "ACTIVE 상태가 아닙니다");

        this.status = MemberStatus.DEACTIVATED;
    }
}
