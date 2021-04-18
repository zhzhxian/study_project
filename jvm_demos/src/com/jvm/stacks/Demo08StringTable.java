package com.jvm.stacks;

//StringTable[]是一个hashTable结构，并且不能扩容
public class Demo08StringTable {
    //常量池中的信息，在类加载的时候会被加载到常量池中，这时a、b、ab都是常量池中的符号，还没有变为java字符串对象




    public static void main(String[] args) {
        //ldc #2 会把a变为"a"字符串对象，将"a"字符串对象放入StringTable中，变为StringTable["a"]
        String s1 = "a";//2124
        //ldc #3 会把b变为"b"字符串对象，将"b"字符串对象放入StringTable中，变为StringTable["a","b"]
        String s2 = "b";//2125
        //ldc #4 会把ab变为"ab"字符串对象，将"ab"字符串对象放入StringTable中，变为StringTable["a","b","ab"]
        String s3 = "ab";//2126
        //new StringBuilder().append("a").append("b").toString() new String("ab")
        String s4 = s1 + s2;//2127
        //ldc #4 StringTable中已经存在"ab"字符串对象，直接从StringTable["a","b","ab"]中获取"ab"字符串对象然后赋值给s5
        String s5 = "a" + "b";//javac在编译期间的优化，结果在编译期间确定为"ab"  2127
        //可以使用intern方法，主动将串池中还没有的字符串对象放入串池
        //此时StringTable["a","b","ab"]中已经存在"ab",所以s4.intern()不会再次向StringTable中存放，此时返回的是串池中的对象，但是s4依然指向堆中的对象
        String s6 = s4.intern();//2127
        System.out.println(s3 == s4);// false
        System.out.println(s3 == s5);// true
        System.out.println(s3 == s6);// true
    }
}
