package io.piano.demo.stackexchange.client.util;

import io.piano.demo.stackexchange.client.exception.GZIPDecodeException;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.util.zip.GZIPInputStream;

public abstract class GZIPUtil {

    @SneakyThrows
    public static <T> T decode(@NonNull byte[] gzipBytes, @NonNull Class<T> clazz) {
        if (!isGZIP(gzipBytes)) throw new GZIPDecodeException();
        try (
            ByteArrayInputStream gzipBytesStream = new ByteArrayInputStream(gzipBytes);
            GZIPInputStream gzipStream = new GZIPInputStream(gzipBytesStream)
        ) {
            return CommonUtil.objectMapper.readValue(gzipStream.readAllBytes(), clazz);
        }
    }

    private static boolean isGZIP(byte[] gzipBytes) {
        return gzipBytes.length > 1 &&
            (gzipBytes[0] & 0xff | (gzipBytes[1] & 0xff) << 8) == GZIPInputStream.GZIP_MAGIC;
    }

    private GZIPUtil() {
        // Utility class
    }

}
