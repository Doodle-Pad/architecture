package com.example.hexagonal.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * packageName    : com.example.hexagonal.domain
 * fileName       : MemberTest
 * author         : Yeong-Huns
 * date           : 26. 9. 6.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 9. 6.        Yeong-Huns       최초 생성
 */
class MemberTest {
    Member member;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        this.passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }
            @Override
            public boolean matches(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };

        this.member = Member.create(new MemberCreateRequest("vosxja1@naver.com", "YeongHuns", "secret"), passwordEncoder);
    }

    @Test
    void createMember() {
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void activate() {
        member.activate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void activateFail() {
        member.activate();

        assertThatThrownBy(member::activate).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deactivate() {
        member.activate();
        member.deactivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }

    @Test
    @DisplayName("Activate 상태가 아닌 유저의 경우 Deactivate로 전환할 수 없다.")
    void deactivateFailWhenStatusNotActivate() {
        assertThatThrownBy(member::deactivate).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("이미 Deactivate인 유저의 경우 Deactivate로 전환할 수 없다.")
    void deactivateFailWhenStatusIsAlreadyDeactivate() {
        member.activate();
        member.deactivate();

        assertThatThrownBy(member::deactivate).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void verifyPassword() {
        assertThat(member.verifyPassword("secret", passwordEncoder)).isTrue();
        assertThat(member.verifyPassword("hello", passwordEncoder)).isFalse();
    }

    @Test
    void updateNickname() {
        assertThat(member.getNickname()).isEqualTo("YeongHuns");

        member.changeNickname("dino");

        assertThat(member.getNickname()).isEqualTo("dino");
    }

    @Test
    void updatePassword() {
        assertThat(member.verifyPassword("secret", passwordEncoder)).isTrue();

        member.changePassword("newSecret", passwordEncoder);

        assertThat(member.verifyPassword("newSecret", passwordEncoder)).isTrue();
    }

    @Test
    void shouldBeActive() {
        member.activate();

        assertThat(member.isActive()).isTrue();

        member.deactivate();

        assertThat(member.isActive()).isFalse();
    }

    @Test
    void isValidEmail() {
        assertThatThrownBy(() -> Member.create(new MemberCreateRequest("vosxja1@naver.com", "YeongHuns", "secret"), passwordEncoder)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void equality() {
        var email1 = new Email("dino@gmail.com");
        var email2 = new Email("dino@gmail.com");

        assertThat(email1).isEqualTo(email2);
    }
}
