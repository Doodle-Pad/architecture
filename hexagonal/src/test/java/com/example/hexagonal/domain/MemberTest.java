package com.example.hexagonal.domain;

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
    @Test
    void createMember() {
        var member = new Member("vosxja1@naver.com", "YeongHuns", "secret");

        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void constructorNullCheck() {
        assertThatThrownBy(() -> new Member(null, "YeongHuns", "secret")).isInstanceOf(NullPointerException.class);
    }

    @Test
    void activate() {
        var member = new Member("vosxja1@naver.com", "YeongHuns", "secret");

        member.activate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void activateFail() {
        var member = new Member("vosxja1@naver.com", "YeongHuns", "secret");

        member.activate();

        assertThatThrownBy(member::activate).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deactivate() {
        var member = new Member("vosxja1@naver.com", "YeongHuns", "secret");

        member.activate();
        member.deactivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }

    @Test
    @DisplayName("Activate 상태가 아닌 유저의 경우 Deactivate로 전환할 수 없다.")
    void deactivateFailWhenStatusNotActivate() {
        var member = new Member("vosxja1@naver.com", "YeongHuns", "secret");

        assertThatThrownBy(member::deactivate).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("이미 Deactivate인 유저의 경우 Deactivate로 전환할 수 없다.")
    void deactivateFailWhenStatusIsAlreadyDeactivate() {
        var member = new Member("vosxja1@naver.com", "YeongHuns", "secret");

        member.activate();
        member.deactivate();

        assertThatThrownBy(member::deactivate).isInstanceOf(IllegalStateException.class);
    }
}
