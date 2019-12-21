package com.jinloes.url_shortener.util;

import java.math.BigInteger;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Base62 {
    private static final BigInteger BASE = BigInteger.valueOf(62);
    private static final String ALPHANUMERIC_CHARS = Stream.of(IntStream.rangeClosed('A', 'Z'),
            IntStream.rangeClosed('a', 'z'), IntStream.rangeClosed('0', '9'))
            .reduce(IntStream::concat)
            .get()
            .mapToObj(c -> (char) c)
            .map(Object::toString)
            .collect(Collectors.joining());

    public static String encodeToString(BigInteger val) {
        Objects.requireNonNull(val, "val to encode was null");
        StringBuilder builder = new StringBuilder();
        do {
            BigInteger[] divideAndRemainder = val.divideAndRemainder(BASE);
            char c = ALPHANUMERIC_CHARS.charAt(divideAndRemainder[1].intValue());
            builder.append(c);
            val = divideAndRemainder[0];
        } while (val.compareTo(BigInteger.ZERO) > 0);
        return builder.toString();
    }
}
