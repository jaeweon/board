package com.board.board.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptUtil {

    // BcryptPasswordEncoder를 싱글턴으로 사용
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 비밀번호를 Bcrypt로 암호화
     *
     * @param rawPassword 원본 비밀번호
     * @return 암호화된 비밀번호
     */
    public static String encrypt(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * 원본 비밀번호와 암호화된 비밀번호를 비교
     *
     * @param rawPassword    원본 비밀번호
     * @param encodedPassword 암호화된 비밀번호
     * @return 일치 여부
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}