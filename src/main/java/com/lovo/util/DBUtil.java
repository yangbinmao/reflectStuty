package com.lovo.util;

import java.sql.*;

public class DBUtil {


    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private PreparedStatement ps = null;
    private Connection conn =null;
    private ResultSet rs = null;


    static{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn(){
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public void close(ResultSet rs,Statement stmt,Connection conn){

            try {
                if (rs!=null){
                  rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt!=null){
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public void open(){
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void close(){
        close(rs,ps,conn);
    }
    public ResultSet executeQuery(String sql,Object... params) throws SQLException {
        ps= conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i+1,params[i]);
        }
        return ps.executeQuery();
    }

    public DBResult executeUpdate(String sql,Object... params) throws SQLException {
        int affectRows=0;
        int generatedKey=0;
        int n=-1;
        //判断是新增还是修改
            try {

                if (sql.toLowerCase().indexOf("insert")>=0){
                    //如果是新增，设置状态
                    ps= conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                }else{
                    //如果不是新增
                    ps=conn.prepareStatement(sql);
                }

                for (int i = 0; i < params.length; i++) {
                    ps.setObject((i+1),params[i]);
                }
                //返回修改受影响行数
                affectRows=ps.executeUpdate();

              if (sql.toLowerCase().indexOf("insert")>=0){
                  //如果是新增
                  ResultSet rs = ps.getGeneratedKeys();
                  if (rs.next()){
                      //返回修改受影响行数
                      generatedKey=rs.getInt(1);
                  }
              }

            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }

        return new DBResult(affectRows,generatedKey);
    }

}
