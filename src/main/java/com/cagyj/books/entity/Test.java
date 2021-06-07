package com.cagyj.books.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// 对应表名
@TableName("cagy.test")
public class Test {

    @TableId(type = IdType.AUTO)  // 说明是主键，且是自增主键
    @TableField("id")  // 对应表中id字段
    private Integer id;

    // 如果字段名与属性名相同或符合驼峰命名转换规则，则可以省略@TableField
    @TableField("name")  // 对应表中name字段
    private String name;

    public Test() {
    }

    public Test(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
