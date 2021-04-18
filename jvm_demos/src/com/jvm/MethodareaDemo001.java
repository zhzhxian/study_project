package com.jvm;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * 演示元空间内存溢出
 * -XX:MaxMetaspaceSize=8m
 */
public class MethodareaDemo001 extends ClassLoader {
    public static void main(String[] args) {
        int j = 0;
        try {
            MethodareaDemo001 test = new MethodareaDemo001();
            for (int i = 0; i < 1000000000; i++, j++) {
                //生成类的二进制字节码
                ClassWriter cw = new ClassWriter(0);
                //版本号，public ，类名，包名，父类，接口
                cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
                //返回byte[]
                byte[] code = cw.toByteArray();
                //执行类加载
                test.defineClass("Class" + i, code, 0, code.length);
            }
        } finally {
            System.out.println(j);
        }
    }
}
