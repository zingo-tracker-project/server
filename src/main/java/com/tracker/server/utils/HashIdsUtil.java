package com.tracker.server.utils;

import org.hashids.Hashids;

import java.util.Optional;

public class HashIdsUtil {

    private static final String SALT = "scdsfadfa134325d";
    private static final int MIN_LENGTH = 8;

    private static final Hashids hashids = new Hashids(SALT, MIN_LENGTH);

    /**
     * 문자열 숫자를 해시로 인코딩
     */
    public static Optional<String> encode(String idStr) {
        try {
            long id = Long.parseLong(idStr);
            return Optional.of(hashids.encode(id));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * 해시 문자열을 원래 숫자 문자열로 복호화
     */
    public static String decode(String encoded) {
        long[] decoded = hashids.decode(encoded);
        if (decoded.length == 0) {
            throw new IllegalArgumentException("해시 복호화 실패: " + encoded);
        }
        return String.valueOf(decoded[0]);
    }
}
