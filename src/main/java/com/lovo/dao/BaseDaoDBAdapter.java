package com.lovo.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by YBM on 2020/7/27 21:47
 */
public class BaseDaoDBAdapter<E, K extends Serializable> extends BaseDaoAdapter<E,K> {

    public Class<?> entityClass;

    /**
     * 构造器，初始化的时候获取E值的具体对象，并赋值给entityClass；
     */
    public BaseDaoDBAdapter(){
        //TODO 1.首先通过反射获取BaseDaoDBAdapter的E的类型
        //因为java没有直接的代码获取，只能通过BaseDaoDBAdapter的父类BaseDaoAdapter得到BaseDaoAdapter的E是什么类型。
        //因为BaseDaoAdapter的E是和BaseDaoDBAdapter的E是同一类型，所以就得到BaseDaoDBAdapter的E的类型
        //TODO 2.获取当前类的直接父类
        //this.getClass().getGenericSuperclass()
        //这句代码是得到带泛型的父类
        //Type是java语言中所有类型的巩固高级接口，它包括原始类型，参数化类型，数组类型， 类型变量以及基本数据类型
        //比如原始类型（raw type--->Class）
        //参数化类型（ParameterizedType）
        //基本数据类型（Primitive type）
        //无论如何,Type是一个顶级接口，superClass反正是一个类型，父类里面的所有类型他都可以找到。
        Type superClass = this.getClass().getGenericSuperclass();
        //所以就可以找到父类里面的泛型（泛型是属于参数化类型）。（就可以利用java子类基础父类机制强制类型转换转为参数化类型对象得到泛型）
        //TODO 将带泛型的父类，转换为泛型的参数
        ParameterizedType pt=(ParameterizedType)superClass;

        //TODO 获取泛型数组
        Type[] tps = pt.getActualTypeArguments();

        entityClass = (Class<?>)tps[0];
        //到此
        // this.getClass().getGenericSuperclass()等于获取 extends BaseDaoAdapter<E,K>
        // (ParameterizedType)superClass 等于将extends BaseDaoAdapter<E,K>转换成<E,K>
        // pt.getActualTypeArguments();等于获取里面所有参赛
        // tps[0]就可以获取对应的对象。

        //TODO 逻辑解析：
        //MyClassDao 的父类是 BaseDao，并且定义了BaseDao的E是MyClass
        //MyClassDaoImpl 的父类是 BaseDaoDBAdapter（本类）
        //因为MyClassDaoImpl实现了MyClassDao，
        // 所以BaseDao也就是BaseDaoDBAdapter的父类
        //所以BaseDaoDBAdapter就可以获取在BaseDao定义的E的属性

    }
}
