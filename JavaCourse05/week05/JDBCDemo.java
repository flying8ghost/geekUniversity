package org.geekbang.week05.c10;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.Calendar;

public class JDBCDemo {

    static HikariDataSource hikariDataSource;

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            insert();
            select();
            update();
            delete();

            batchInsert();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void insert() {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into signature(create_datetime,account_id,sign) values('2021-09-04 21:11:11','100','哈哈')";
            Statement s = connection.createStatement();
            int rows = s.executeUpdate(sql);
            if (1 == rows) {
                System.out.println("插入成功");
            } else {
                System.err.println("插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void select() {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select create_datetime,account_id,sign from signature where id = 5";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.first();

            String sign = rs.getString("sign");
            System.out.println("sign:" + sign);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void update() {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "update signature s set s.sign = '嘻嘻' where id = 5";
            Statement ps = connection.createStatement();
            int rows = ps.executeUpdate(sql);
            if (1 == rows) {
                System.out.println("更新成功");
            } else {
                System.err.println("更新失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void delete() {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "delete s from signature s where id = 5";
            Statement ps = connection.createStatement();
            int rows = ps.executeUpdate(sql);
            if (1 == rows) {
                System.out.println("删除成功");
            } else {
                System.err.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void batchInsert() {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            String sql = "insert into signature(create_datetime,account_id,sign) values(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < 5; i++) {
                ps.setDate(1, new Date(Calendar.getInstance().getTime().getTime()));
                ps.setString(2, "100_" + i);
                ps.setString(3, "哈哈");
                ps.addBatch();
            }

            int[] rows = ps.executeBatch();
            if (5 == rows.length) {
                System.out.println("批量插入成功");
                connection.commit();
            } else {
                System.err.println("批量插入失败");
                connection.rollback();
            }

        } catch (SQLException e) {
            System.err.println("回滚 - 批量插入失败");
            e.printStackTrace();
            try {
                if (null != connection) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                System.err.println("回滚失败 - 批量插入失败");
                e1.printStackTrace();
            }

        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws SQLException {
//        //非连接池
//        return DriverManager.getConnection(
//                "jdbc:mysql://127.0.0.1:3306/sms_service?useSSL=false"
//                , "root"
//                , "12345678");

        //连接池
        if (null == hikariDataSource) {
            configHikariDataSource();
        }
        return hikariDataSource.getConnection();
    }


    public static void configHikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/sms_service?useSSL=false");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("12345678");
        hikariConfig.setMinimumIdle(5);

        hikariDataSource = new HikariDataSource(hikariConfig);
    }
}