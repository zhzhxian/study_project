package com.zzx.biodemos;

public class DeepClone {
    public static void main(String[] args) throws Exception {
        Student student = new Student();
        student.setName("张三丰");
        System.out.println(student);
        Student student1 = (Student) student.deepClone();
        System.out.println(student1);
        student1.setName("张无忌");
        System.out.println(student);
        System.out.println(student1);
    }
}
