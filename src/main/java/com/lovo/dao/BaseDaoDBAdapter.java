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

        //TODO 逻辑解析： 还是有问题。解析不清楚
        //因为不知道传入进来的是什么类型的实体类，
        //，同时在E的位置写上了的Myclass实体类
        //只要在BaseDao<MyClass,Interger>内传入了MyClass实体类。
        //到时候在MyClassDaoImpl内，因为MyClassDaoImpl继承了BaseDaoDBAdapter<MyClass,Integer>（就是当前类），并且实现了MyClassDao接口
        //然后BaseDaoDBAdapter类的父类BaseDaoAdapter类也实现了BaseDao
        //所以就可以在BaseDaoDBAdapter的父类上获取E的值



        //在MyClassDao接口内继承了BaseDao<E,K>接口,所以BaseDao接口是MyClassDao接口的父类
        //MyClassDaoImpl类实现了MyClassDao接口，所以BaseDao也就成了MyClassDaoImpl类的父类
        //BaseDao接口> MyClassDao接口= MyClassDaoImpl类的父类

        //BaseDaoDBAdapter类（当前类）是MyClassDaoImpl类的父类
        //BaseDaoAdapter类是BaseDaoDBAdapter类（当前类）的父类
        //BaseDaoAdapter类实现了BaseDao接口
        //BaseDaoDBAdapter类（当前类）= BaseDao接口 > MyClassDaoImpl类
    }
}
