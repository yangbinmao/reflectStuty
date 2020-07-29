package com.lovo.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class test {
    public static void main(String[] args) throws ClassNotFoundException {

        Class clazz = Class.forName("com.lovo.bean.MyClass");
        System.out.println("类名："+clazz.getSimpleName());
        System.out.println("完整类名："+clazz.getName());
        System.out.println(Modifier.toString(clazz.getModifiers()));
        //==============================通过反射获取类的构造方法===========================================
        Constructor[] cs = clazz.getConstructors();
        for (Constructor c : cs) {
            //获取构造方法的名字
            String conName = c.getName();
            //获取构造方法的访问修饰符
            String conMod = Modifier.toString(c.getModifiers());
            //获取构造方法的参数
            Class[] ccs = c.getParameterTypes();
            System.out.println();
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

        //==============================通过反射获取类的成员方法===========================================
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
    }
}
