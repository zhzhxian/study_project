package com.jvm.stacks;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * 演示元空间内存溢出
 * -XX:MaxMetaspaceSize=8m
 * -XX:-UseCompressedClassPointers（压缩开关）
 * -XX:CompressedClassSpaceSize（Compressed Class Space 空间大小限制）
 */
//类加载器可以用来加载类的二进制字节码
public class Demo06 extends ClassLoader {
    public static void main(String[] args) {
        int j = 0;
        try {
            Demo06 test = new Demo06();
            for (int i = 0; i < 10000; i++, j++) {
                //生成类的二进制字节码
                ClassWriter cw = new ClassWriter(i);
                //cw.visit(版本号,累的修饰符,类的名字,包名,父类名,类实现的接口名称
                cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
                //返回byte
                byte[] code = cw.toByteArray();
                //执行类的加载 返回Class类的对象
                test.defineClass("Class" + i, code, 0, code.length);
            }
        } finally {
            System.out.println(j);
        }
    }
}
