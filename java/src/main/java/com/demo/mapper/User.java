package com.demo.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("app_user")
public class User {
     int id;
     String name;
     String password;
     String ide;
     String part;
     String salt;
}
