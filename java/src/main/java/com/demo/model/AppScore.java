package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName app_score
 */
@TableName(value ="app_score")
@Data
public class AppScore {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Object scoreitem;

    /**
     * 
     */
    private Date time;

    /**
     * 
     */
    private String examtype;

    /**
     * 
     */
    private Long groupId;

    /**
     * 
     */
    private Long subcourseId;

    /**
     * 
     */
    private String remark;
}