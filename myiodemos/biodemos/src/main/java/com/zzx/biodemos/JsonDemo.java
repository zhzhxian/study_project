package com.zzx.biodemos;

import com.alibaba.fastjson.JSON;

public class JsonDemo {
    public static void main(String[] args) throws Exception {
        executeWithFastJson();
    }

    private static Person initPerson() {
        Person person = new Person();
        person.setId("1");
        person.setName("张三丰");
        return person;
    }

    private static void executeWithFastJson() throws Exception {
        Person person = initPerson();
        String text = null;
        for (int i = 0; i < 100; i++) {
            text = JSON.toJSONString(person);//序列化
        }
        System.out.println(text);
        Person person1 = JSON.parseObject(text,Person.class);//反序列化
        person1.setName("张无忌");
        System.out.println(person);
        System.out.println(person1);
    }
}
