package com.lovo.dao;

import com.lovo.util.ReflectionUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        //TODO 1.1 获取当前类的直接父类
        //this.getClass().getGenericSuperclass()
        //这句代码是得到带泛型的父类
        //Type是java语言中所有类型的巩固高级接口，它包括原始类型，参数化类型，数组类型， 类型变量以及基本数据类型
        //比如原始类型（raw type--->Class）
        //参数化类型（ParameterizedType）
        //基本数据类型（Primitive type）
        //无论如何,Type是一个顶级接口，superClass反正是一个类型，父类里面的所有类型他都可以找到。
        Type superClass = this.getClass().getGenericSuperclass();
        //所以就可以找到父类里面的泛型（泛型是属于参数化类型）。（就可以利用java子类基础父类机制强制类型转换转为参数化类型对象得到泛型）
        //TODO 1.2 将带泛型的父类，转换为泛型的参数
        ParameterizedType pt=(ParameterizedType)superClass;

        //TODO 1.3 获取泛型数组
        Type[] tps = pt.getActualTypeArguments();

        entityClass = (Class<?>)tps[0];
        //到此
        // this.getClass().getGenericSuperclass()等于获取  BaseDaoAdapter<E,K>
        // (ParameterizedType)superClass 等于将 BaseDaoAdapter<E,K>转换成<E,K>
        // pt.getActualTypeArguments();等于获取里面所有参赛
        // tps[0]就可以获取对应的对象。

        //TODO 总结：逻辑解析：
        //MyClassDao 的父类是 BaseDao，并且定义了BaseDao的E是MyClass
        //MyClassDaoImpl 的父类是 BaseDaoDBAdapter（本类）
        //因为MyClassDaoImpl实现了MyClassDao，
        //所以BaseDao也就是BaseDaoDBAdapter的父类
        //所以BaseDaoDBAdapter就可以获取在BaseDao定义的E的属性

    }

    /**
     * 读数据库内数据，通过反射直接赋值到实体类的属性上
     * @param rs 数据库结果集（游标）
     * @return 返回数据库封装好的对象实体（业务实体）
     * @throws SQLException
     */
    public E fetchSingleEntity(ResultSet rs) throws SQLException {
        E entity = null ;
        /**
         * 我们可以通过ResultSetMetaData 获取数据集相关信息
         * ResultSet 是关于表（虚拟表）的查询结果
         * ResultSetMetaData 关于表（虚拟表）的字段名称和字段类型
         */
        ResultSetMetaData metaData = rs.getMetaData();


        if (rs.next()){
            try {
                //通过反射获取对象
                entity = (E)entityClass.newInstance();
                //通过反射获取对象的所有属性名
                String [] filedNames = ReflectionUtil.getFiledNames(entity);
                //下面在通过反射，将数据库里面的值放入到对象的属性里面去
                for (int i = 0; i < filedNames.length && i < metaData.getColumnCount(); i++) {
                    ReflectionUtil.setValue(entity,filedNames[i],rs.getObject(filedNames[i]));
                }

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    public List<E> fetchMultiEntities(ResultSet rs) throws  SQLException{

         List<E> list = new ArrayList<E>();

        ResultSetMetaData metaData = rs.getMetaData();

        while (rs.next()){
            try {
                //通过反射获取对象
                E  entity = (E)entityClass.newInstance();
                //通过反射获取对象的所有属性名
                String [] filedNames = ReflectionUtil.getFiledNames(entity);
                //下面在通过反射，将数据库里面的值放入到对象的属性里面去
                for (int i = 0; i < filedNames.length && i < metaData.getColumnCount(); i++) {
                    ReflectionUtil.setValue(entity,filedNames[i],rs.getObject(filedNames[i]));
                }
                list.add(entity);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
