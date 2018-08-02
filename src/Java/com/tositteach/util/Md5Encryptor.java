package com.tositteach.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encryptor {
    public static String encrypt(String raw) {
        if (raw == null || raw.length() == 0) return "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5.update(raw.getBytes());
            return fillZero(new BigInteger(1, md5.digest()).toString(16));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String fillZero(String s) {
        StringBuilder builder = new StringBuilder(s);
        while (builder.length() < 32) builder.insert(0, "0");
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.printf(encrypt("123456")); //e10adc3949ba59abbe56e057f20f883e
    }
}
