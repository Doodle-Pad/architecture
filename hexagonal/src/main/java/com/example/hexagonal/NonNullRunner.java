package com.example.hexagonal;

import org.jspecify.annotations.NonNull;

/**
 * packageName    : com.example.hexagonal
 * fileName       : NonNullRunner
 * author         : Yeong-Huns
 * date           : 26. 9. 6.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 9. 6.        Yeong-Huns       최초 생성
 */
public class NonNullRunner {
    public static void main(String[] args) {
        String name = null;
        print(name);
    }

    private static void print(@NonNull String name) {
        System.out.print(name);
    }


}
