package com.zhao.mawen.provider;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionProviderTest {
    private static final int deviationNum = 2000;
    @Test
    void deviation() {

    }

    @Test
    void base64() throws UnsupportedEncodingException {
        String account = deviation("zhaoxinpeng",deviationNum);
        String password = deviation("192837",deviationNum);
        String base64Str1 = base64(account);
        String base64Str2 = base64(password);
        System.out.println("account加密后：" + base64Str1);
        System.out.println("password加密后：" + base64Str2);
        //解密
        System.out.println("account解密：" + deStr(base64Str1));
        System.out.println("password解密：" + deStr(base64Str2));
    }

    @Test
    void deStr() {
    }

    public static String deviation(String str,int deviationNum){
        char[] arr = str.toCharArray();
        for(int i = 0;i < arr.length;i++){
            arr[i] = (char)(arr[i] ^ deviationNum);
        }
        return new String(arr);
    }

    /**
     * base64加密
     * @param str
     * @return
     */
    public static String base64(String str) throws UnsupportedEncodingException {
        String s = deviation(str,deviationNum);
        String strBase = new String(Base64.encode(s.getBytes("utf-8")));
        return strBase;
    }

    public static String deStr(String str) {
        String enStr = new String(Base64.decode(str));
//        String[] strArr = enStr.split("&");
        return enStr;
    }
}