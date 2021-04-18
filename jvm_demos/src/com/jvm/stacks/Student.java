package com.jvm.stacks;

public class Student {
    private byte[] arr = new byte[10*1024*1024];

    public byte[] getArr() {
        return arr;
    }

    public void setArr(byte[] arr) {
        this.arr = arr;
    }
}
