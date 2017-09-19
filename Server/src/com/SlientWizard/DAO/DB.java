package com.SlientWizard;

import java.sql.*;

public class DB {
    private Connection DBconnection;
    private Statement statement;
    private static String url = "jdbc:mysql://127.0.0.1:3306/";
    private static String arg = "?characterEncoding=utf8&useSSL=true";
    private static String account = "root";
    private static String pwd = "root";

    public static void main(String[] args) throws SQLException {
        DB db = new DB("open_source_intelligence");
        System.out.println(db.select("baidu","*"));
        db.close();
    }

    public DB(String Database){
        try {
            init(Database);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void init(String Database) throws ClassNotFoundException, SQLException {
        // In order to get access to MySQL, must add mysql-connector-java-5.1.44-bin.jar to Project Structure/Class Path
        Class.forName("com.mysql.jdbc.Driver");
        DBconnection = DriverManager.getConnection  (url+Database+arg,account,pwd);
        statement = DBconnection.createStatement();
    }

    public ResultSet select(String table, String key) throws SQLException {
        return statement.executeQuery("select "+key+" from "+table);
    }
    /*
    public String insert(){}
    public String delete(){}
    public String update(){}
    */
    public void close() {
        try{
            statement.close();
            DBconnection.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
