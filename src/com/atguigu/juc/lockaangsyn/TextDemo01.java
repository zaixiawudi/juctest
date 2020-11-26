package com.atguigu.juc.lockaangsyn;

import java.util.Random;

public class TextDemo01 {
    public static void main(String[] args) {
        TextDemo01 textDemo01 = new TextDemo01();
        new Thread(()->{
            textDemo01.test01();
        }).start();
    }
    public void test01(){
        System.out.println(new Random(1000));
        test01();
    }
}
