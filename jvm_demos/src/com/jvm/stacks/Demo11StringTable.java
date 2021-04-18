package com.jvm.stacks;

/**
 * 演示StringTable垃圾回收
 * -Xmx10m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails -verbose:gc
 */
public class Demo11StringTable {

    public static void main(String[] args) {
        int i = 0;
        try{
            for (int j = 0; j < 10000; j++) {
                String.valueOf(j).intern();
                i++;
            }
        } catch (Exception e) {
            System.out.println(i);
        }
    }
}
