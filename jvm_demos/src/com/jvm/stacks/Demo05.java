package com.jvm.stacks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Demo05 {
    public static void main(String[] args) throws IOException {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            list.add(new Student());
        }
        System.in.read();
    }
}
