package com.lovo.impl;

import com.lovo.bean.MyClass;
import com.lovo.dao.BaseDaoDBAdapter;
import com.lovo.dao.MyClassDao;
import com.lovo.util.DBResult;
import com.lovo.util.DBSessionFactory;

import java.sql.SQLException;
import java.util.List;

public class MyClassDaoImpl extends BaseDaoDBAdapter<MyClass,Integer> implements MyClassDao {
    @Override
    public MyClass findById(Integer id) {
        MyClass myClass = null;
        try {
            myClass = this.fetchSingleEntity(DBSessionFactory.openSession()
                    .executeQuery("select * from tb_class where id=?",id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myClass;
    }


    @Override
    public List<MyClass> findAll() {
        List<MyClass> list=null;
        try {
            list= this.fetchMultiEntities(DBSessionFactory.openSession()
                    .executeQuery("select * from tb_class"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public boolean deleteById(Integer id) {

        try {
            DBResult dbResult = DBSessionFactory.openSession().executeUpdate("delete from tb_class where id=?", id);
            if (dbResult.getAffectRows()>=1){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        MyClassDaoImpl myClassDaoImpl = new MyClassDaoImpl();
        MyClass myClass = myClassDaoImpl.findById(1);
        System.out.println(myClass);
        System.out.println(myClassDaoImpl.findAll());


        System.out.println(myClassDaoImpl.deleteById(1));
        DBSessionFactory.closeSession();

    }

}
