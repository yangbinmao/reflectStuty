package com.lovo.util;

/**
 * Created by YBM on 2020/7/28 12:24
 */

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射帮助工具类
 */
public class ReflectionUtil {

    /**
     * 获取对象所有的属性名称
     * @param obj 业务对象
     * @return
     */
    public static String[] getFiledNames(Object obj){
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        List<String> filedName = new ArrayList<String>();
        for (int i = 0; i < fields.length; i++) {
            filedName.add(fields[i].getName());
        }

        return filedName.toArray(new String[filedName.size()]);
    }



    public static void  setValue(Object obj,String fieldName,Object value){
        //获取反射对象
        Class<?> clazz = obj.getClass();
        //fieldName有可能带有包名的前缀，比如com.lovo.bean.XXX
        String [] fs =fieldName.split("\\.");
        try {
            Field f = clazz.getDeclaredField(fs[fs.length - 1]);
            f.setAccessible(true);
            f.set(obj,value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
