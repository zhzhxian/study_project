package com.zzx.biodemos;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

public class Student implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 通过序列化和反序列化实现深度克隆
     *
     * @return
     * @throws Exception
     */
    public Object deepClone() throws Exception {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
        objectOutputStream.writeObject(this);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteOutputStream.getBytes()));
        return objectInputStream.readObject();
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
