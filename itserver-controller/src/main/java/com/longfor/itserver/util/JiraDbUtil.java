package com.longfor.itserver.util;

import com.longfor.itserver.common.helper.JoddHelper;
import jodd.props.Props;

import java.sql.*;

public class JiraDbUtil {
    /***
     * 关闭连接和结果集
     * @param stmt
     * @param rs
     * @throws Exception
     */
    public static void close(Statement stmt, ResultSet rs ) throws Exception{
        if (stmt != null) {
            stmt.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    /***
     * 获取jira数据源
     * @return
     * @throws SQLException
     */
    public static Connection getConnection(Connection CONN) throws SQLException {
        Props props = JoddHelper.getInstance().getJoddProps();
        if (CONN == null || CONN.isClosed()) {
            try {
                Class.forName(props.getValue("jdbc.jira.driverClass"));
                CONN = DriverManager.getConnection(
                        props.getValue("jdbc.jira.url"),
                        props.getValue("jdbc.jira.userName"),
                        props.getValue("jdbc.jira.passWord"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return CONN;
    }
}
