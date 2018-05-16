package com.longfor.itserver.util;

import com.longfor.itserver.common.vo.Feature;
import com.longfor.itserver.common.vo.JiraIssue;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JiraIssueHelp {
    private static Connection CONN = null;

    /**
     * type=0:近90天创建的问题,
     * type=1:近90天解决的问题
     * @param logger
     * @param code
     * @param type
     * @return
     */
    public static List<JiraIssue> doJiraIssue(Logger logger,String code,Integer type){
        StringBuilder selectSB = new StringBuilder();
        if (0==type) {
            logger.info("---------------- 获取jira数据,近90天创建的问题... ----------------");
            selectSB.append("select p.pname 'name',j.created 'date',COUNT(*) 'count' " +
                    " from jiraissue j, project p " +
                    " where DATE_SUB(CURDATE(), INTERVAL 90 DAY) <= j.created " +
                    " AND p.pkey = '" + code + "' AND p.id = j.project AND issuetype in(10401,10200) " +
                    " GROUP BY DATE_FORMAT(j.created,'%Y-%m-%d') " +
                    " ORDER BY j.created");
        }
        if (1==type) {
            logger.info("---------------- 获取jira数据,近90天解决的问题... ----------------");
            selectSB.append(" select p.pname 'name',j.resolutiondate 'date',COUNT(*) 'count'" +
                    " from jiraissue j, project p" +
                    " where DATE_SUB(CURDATE(), INTERVAL 90 DAY) <= j.resolutiondate" +
                    " AND p.pkey = '" + code + "' AND p.id = j.project AND issuestatus in(5,6)" +
                    " GROUP BY DATE_FORMAT(j.resolutiondate,'%Y-%m-%d') " +
                    " ORDER BY j.resolutiondate");
        }


        Statement stmt = null;
        ResultSet rs = null;

        List<JiraIssue> jiraIssueList = new ArrayList<>();

        try {
            stmt = JiraDbUtil.getConnection(CONN).createStatement();
            rs = stmt.executeQuery(selectSB.toString());
            while (rs.next()) {
                try {
                    JiraIssue jiraIssue =dealJiraIssue(rs);
                    jiraIssueList.add(jiraIssue);
                }catch (Exception e){
                    e.printStackTrace();
                    logger.info("查询数据异常"+rs.getString(1));
                    continue;
                }
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JiraDbUtil.close(stmt,rs);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return jiraIssueList;
    }

    private static JiraIssue dealJiraIssue(ResultSet rs) throws SQLException{
        JiraIssue jiraIssue = new JiraIssue();
        jiraIssue.setName(rs.getString(1));
        jiraIssue.setDate(rs.getString(2));
        jiraIssue.setCount(rs.getString(3));
        return jiraIssue;
    }
}
