package com.cloud.common.api.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public final class HashUtils {

    public static String md5(String data) {
        return Hashing.md5().hashString(data, StandardCharsets.UTF_8).toString();
    }

    public static void main(String[] args) {
        String md5 = md5("dongyang");
        System.out.println(md5);
    }
}
