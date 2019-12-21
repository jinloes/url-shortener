package com.jinloes.url_shortener.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.UUID;

public class UuidUtils {
    public static BigInteger asBigInt() {
        UUID uuid = UUID.randomUUID();
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
        byteBuffer.putLong(uuid.getLeastSignificantBits());
        byteBuffer.putLong(uuid.getMostSignificantBits());
        return new BigInteger(1, byteBuffer.array());
    }
}
