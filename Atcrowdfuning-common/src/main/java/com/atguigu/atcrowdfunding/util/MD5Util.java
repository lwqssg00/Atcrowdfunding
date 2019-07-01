package com.atguigu.atcrowdfunding.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MD5Util {
    public static List<String> generate(String password) {

        List<String> hash_sat = new ArrayList<>();
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
                  sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
                  if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        hash_sat.add(salt);
        password = md5Hex(password + salt);
        char[] cs = new char[48];
                  for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        hash_sat.add(new String(cs));
                  return hash_sat;
    }
    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    private static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }
    public static boolean verify(String password, String md5) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        return md5Hex(password + salt).equals(new String(cs1));
    }

//    public static void main(String[] args) {
//        String ss = MD5Util.generate("123456").get(1);
//        System.out.println(MD5Util.generate("123456"));
//        System.out.println("是否是同一字符串："+MD5Util.verify("123456",ss));
//    }



    //根据传入的盐值验证密码
    public static String generate2(String password,String salt) {

        password = md5Hex(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }
}
