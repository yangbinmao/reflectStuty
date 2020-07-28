package com.lovo.util;

/**
 * Created by YBM on 2020/7/28 21:18
 */
public class DBSessionFactory {

    private static final ThreadLocal<DBUtil> threadLocal = new ThreadLocal<DBUtil>();

    public static DBUtil openSession(){
        DBUtil session =threadLocal.get();
        if (session==null){
            session=new DBUtil();
            threadLocal.set(session);
        }
        session.open();
        return  session;
    }

    public static void closeSession(){
        DBUtil session =threadLocal.get();
        threadLocal.set(null);
        if (session!=null){
            session.close();
            System.out.println("数据库已经关闭");
        }
    }
}
