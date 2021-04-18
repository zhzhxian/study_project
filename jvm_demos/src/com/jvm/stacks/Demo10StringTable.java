package com.jvm.stacks;

public class Demo10StringTable {
    public static void main(String[] args) {
        String s0 = "ab";
        String s = new String("a") + new String("b");//堆 new String("a") new String("b") new String("ab")
        //（JDK1.8）此时串池中已经存在"ab"，则不会将s指向的堆中的"ab"对象放入串池中，返回串池中的"ab"对象,然后赋值给s2。s仍然指向堆中的"ab"对象
        String s2 = s.intern();
        System.out.println(s2 == "ab");//true
        System.out.println(s == "ab");//false
    }
}
