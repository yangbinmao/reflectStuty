package com.lovo.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

public class getEntityTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class clazz = Class.forName("com.lovo.bean.MyClass");
        System.out.println("类名："+clazz.getSimpleName());
        System.out.println("完整类名："+clazz.getName());
        System.out.println(Modifier.toString(clazz.getModifiers()));
        //==============================通过反射获取类的构造方法===========================================
        System.out.println("通过反射获取类的构造方法");
        System.out.println("获得所有构造器");
        Constructor[] cs = clazz.getConstructors();
        for (Constructor c : cs) {
            //获取构造方法的名字
            String conName = c.getName();
            //获取构造方法的访问修饰符
            String conMod = Modifier.toString(c.getModifiers());
            //获取构造方法的参数
            Class[] ccs = c.getParameterTypes();
            System.out.print(conMod+" "+conName +"(");
            //循环所有的参数类型
            for (int i = 0; i < ccs.length; i++) {
                if (i>0){
                    System.out.print(",");
                }
                if (ccs[i].isArray()){
                    System.out.print(ccs[i].getComponentType().getName());
                }else {
                    System.out.print(ccs[i].getName());
                }

            }
            System.out.println(")");
        }
        //通过class对象得到带参的构造器
        System.out.println("创建带参构造器");
        Constructor constructor = clazz.getConstructor(int.class, String.class, Date.class);
        constructor.setAccessible(true);
        System.out.println(constructor);
        Object entity = constructor.newInstance(12, "1123", new Date());
        System.out.println("entity = " + entity);

        //==============================通过反射获取类的成员方法===========================================
        System.out.println("获得所有方法");
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
        System.out.println("得到方法使用方法");
        Method setId = clazz.getMethod("setId", int.class);
        setId.setAccessible(true);
        Object invoke = setId.invoke(entity, 2);
        System.out.println("invoke = " + invoke);
    }
}
