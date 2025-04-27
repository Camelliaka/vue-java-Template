package com.demo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Ser {
    @Autowired
    Dao dao;

    @RequestMapping("/user")
    public R getAll() {
        List<User> users = dao.selectList(null);
        return R.ok(users);
    }
}
