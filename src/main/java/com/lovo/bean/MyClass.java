package com.lovo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 实体类的建设属性名和属性顺序一定要和数据库内字段的名称和顺序一致，
 * 不然在反射的时候设置属性值的时候对应不上就无法正常映射
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyClass {
    private int id;
    private String name;
    private Date creatTime;
}
