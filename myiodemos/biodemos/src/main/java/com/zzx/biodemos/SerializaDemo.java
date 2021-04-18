package com.zzx.biodemos;

import java.io.*;

public class SerializaDemo {
    public static void main(String[] args) throws Exception {
        String fileName = "person";
        serializePerson(fileName);
        Person person = deSerializePerson(fileName);
        System.out.println(person);
    }

    /**
     * 序列化
     *
     * @param fileName
     * @throws IOException
     */
    private static void serializePerson(String fileName) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(fileName
        )));
        Person person = new Person();
        person.setId("1");
        person.setName("张三");
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();
        System.out.println("第一次序列化" + new File(fileName).length());
//        Person person1 = new Person();
//        person1.setId("2");
        person.setName("李四");
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();
        System.out.println("第二次序列化" + new File(fileName).length());
        objectOutputStream.close();
    }

    /**
     * 反序列化
     *
     * @param fileName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static Person deSerializePerson(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(fileName)));
        Person person = (Person) objectInputStream.readObject();
        Person person1 = (Person) objectInputStream.readObject();
        System.out.println("反序列化" + person);
        System.out.println("反序列化1" + person1);
        objectInputStream.close();
        return person;
    }
}
