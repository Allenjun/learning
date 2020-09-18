package com.allen.codec;

import org.junit.Test;

import java.nio.charset.Charset;

public class StringTests {

    @Test
    public void internTests() {
        String abc = new String("abc");
        String abc1 = "abc";

        String intern = abc.intern();
    }

    @Test
    public void charsetTests() {
        String chan = "陈chan";
        byte[] gbks = chan.getBytes(Charset.forName("GBK"));
        byte[] utf8s = chan.getBytes(Charset.forName("UTF-8"));

        System.out.println(gbks[0]);
        System.out.println(gbks);
        System.out.println(utf8s[0]);
        System.out.println(utf8s);

        String gbk = new String(gbks, Charset.forName("GBK"));
        String utf8 = new String(utf8s, Charset.forName("UTF-8"));

        System.out.println(gbk);
        System.out.println(utf8);

        String fgbk = new String(gbks, Charset.forName("UTF-8"));
        String futf = new String(utf8s, Charset.forName("GBK"));

        System.out.println(fgbk);
        System.out.println(futf);
    }

    @Test
    public void base64Tests() {
        String chan = "陈chan";
    }


}
