package com.lovo.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by YBM on 2020/7/30 23:07
 */
public class getImplTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clazz = Class.forName("com.lovo.impl.MyClassDaoImpl");
        Object entity = clazz.newInstance();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            //获取访问修饰符
            String mMod = Modifier.toString(method.getModifiers());
            //获取返回值类型
            Class mType = method.getReturnType();

            String returnType = "";

            if (mType.isArray()){
                returnType=mType.getComponentType().getName();
            }else{
                returnType=mType.getName();
            }
            //方法名字
            String mName= method.getName();
            System.out.print(mMod+" "+ returnType +" "+ mName+"(");
            //获取方法的参数列表
            Class[] ps = method.getParameterTypes();
            for (int i = 0; i < ps.length; i++) {
                if (i>0){
                    System.out.print(",");
                }

                if (ps[i].isArray()){
                    System.out.print(ps[i].getComponentType().getName());
                }else {
                    System.out.print(ps[i].getName());
                }
            }
            System.out.println(")");
        }

        Method findAll = clazz.getDeclaredMethod("findAll");
        findAll.setAccessible(true);
        Object invoke = findAll.invoke(entity);
        System.out.println(invoke);
    }
}
