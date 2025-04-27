package com.demo.mapper;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;


/**
 * 统一API响应结果封装类
 *
 * @param <T> 数据类型
 */
@Data
@Accessors(chain = true) // 支持链式调用
public class R<T> implements Serializable {
    // 常用状态码常量
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_ERROR = 500;
    public static final int CODE_UNAUTHORIZED = 401;
    public static final int CODE_FORBIDDEN = 403;
    public static final int CODE_NOT_FOUND = 404;

    // 状态码
    private int code;
    // 消息内容
    private String message;
    // 返回数据
    private T data;
    // 时间戳
    private long timestamp = System.currentTimeMillis();
    // 扩展信息（用于非标准字段）
    private HashMap<String, Object> extras;



    // 成功响应（无数据）
    public static <T> R<T> ok() {
        return new R<T>()
                .setCode(CODE_SUCCESS)
                .setMessage("操作成功");
    }

    // 成功响应（带数据）
    public static <T> R<T> ok(T data) {
        return (R<T>) ok().setData(data);
    }

    // 失败响应
    public static <T> R<T> error(String message) {
        return new R<T>()
                .setCode(CODE_ERROR)
                .setMessage(message);
    }

    // 自定义状态码
    public static <T> R<T> code(int code) {
        return new R<T>().setCode(code);
    }

    // ----------------- 链式方法增强 -----------------

    // 添加扩展字段
    public R<T> addExtra(String key, Object value) {
        if (this.extras == null) {
            this.extras = new HashMap<>();
        }
        this.extras.put(key, value);
        return this;
    }

    // 快速判断是否成功
    public boolean isSuccess() {
        return this.code == CODE_SUCCESS;
    }
}
