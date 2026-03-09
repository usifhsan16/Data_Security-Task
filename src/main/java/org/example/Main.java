package org.example;

import Security.CaeserCipher;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        CaeserCipher c = new CaeserCipher();

        int res = c.analyse("zet","clo");
        System.out.println(res);
    }
}