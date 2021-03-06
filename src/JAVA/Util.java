package JAVA;

import java.nio.charset.StandardCharsets;

public class Util {
    public static String removeLastChar(String s) {
        return s.substring(0, s.length() - 1);
    }

    public static String utf8Encode(String rawString) {
        byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}

