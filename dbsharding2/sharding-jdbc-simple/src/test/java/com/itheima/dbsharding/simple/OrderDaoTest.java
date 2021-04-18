package com.itheima.dbsharding.simple;

import com.itheima.dbsharding.simple.dao.DictDao;
import com.itheima.dbsharding.simple.dao.OrderDao;
import com.itheima.dbsharding.simple.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShardingJdbcSimpleBootstrap.class})
public class OrderDaoTest {

    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @Autowired
    DictDao dictDao;

    @Test
    public void testInsertOrder() {
        for (int i = 1; i < 20; i++) {
            orderDao.insertOrder(new BigDecimal(i), 4L, "SUCCESS");
        }
    }

    @Test
    public void testSelectOrderbyIds() {
        List<Long> ids = new ArrayList<>();
        ids.add(555112271932555264L);
//        ids.add(555112272049995777L);
        ids.add(555111905534935041L);

        List<Map> maps = orderDao.selectOrderbyIds(ids);
        System.out.println(maps);
    }

    @Test
    public void testSelectOrderbyUserAndIds() {
        List<Long> ids = new ArrayList<>();
        ids.add(555112271932555264L);
        ids.add(555111905442660353L);

        List<Map> maps = orderDao.selectOrderbyUserAndIds(4L, ids);
        System.out.println(maps);
    }

//    @Test
//    public void testSelectOrderbyUserAndIdsNew() {
//        List<Long> ids = new ArrayList<>();
//        ids.add(555112271932555264L);
//        ids.add(555111905442660353L);
//
//        List<Long> userIds = new ArrayList<>();
//        userIds.add(4L);
//        userIds.add(1L);
//
//        List<Map> maps = orderDao.selectOrderbyUserAndIdsNew(userIds, ids);
//        System.out.println(maps);
//    }

    @Test
    public void testInsertUser() {
        for (int i = 0; i < 20; i++) {
            Long id = i + 1L;
            userDao.insertUser(id, "姓名" + id);
        }

    }

    @Test
    public void testDeleteUser() {
        userDao.deleteAll();
    }

    @Test
    public void testSelectUserbyIds() {
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        List<Map> users = userDao.selectUserbyIds(userIds);
        System.out.println(users);
    }

    @Test
    public void testSelectUserInfobyIds() {
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        List<Map> users = userDao.selectUserInfobyIds(userIds);
        System.out.println(users);
    }

    @Test
    public void testInsertDict() {
        dictDao.insertDict(5L, "user_type", "1", "超级管理员test");
//        dictDao.insertDict(2L, "user_type", "4", "二级管理员");
    }

    @Test
    public void testDeleteDict() {
        dictDao.deleteDict(3L);
        dictDao.deleteDict(4L);
    }

    @Test
    public void testQueryDict() {
        System.out.println(dictDao.queryDict());
    }
}
