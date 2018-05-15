package com.longfor.itserver.util;

import com.longfor.itserver.common.helper.JoddHelper;
import com.longfor.itserver.common.vo.Feature;
import jodd.props.Props;
import org.slf4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JiraHelp {
    private static Connection CONN = null;

    public static List<Feature> doJira(Logger logger){
        logger.info("---------------- 获取jira数据... ----------------");
        StringBuilder selectSB = new StringBuilder();
        selectSB.append("SELECT * FROM feature");

        Statement stmt = null;
        ResultSet rs = null;

        List<Feature> featureList = new ArrayList<>();

        try {
            stmt = JiraDbUtil.getConnection(CONN).createStatement();
            rs = stmt.executeQuery(selectSB.toString());
            while (rs.next()) {
                try {
                    Feature feature =dealFeature(rs);
                    featureList.add(feature);
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

        return featureList;
    }

    private static Feature dealFeature(ResultSet rs) throws SQLException{
        Feature feature = new Feature();
        feature.setId(rs.getBigDecimal(1));
        feature.setFeatureName(rs.getString(2));
        feature.setFeatureType(rs.getString(3));
        feature.setUserKey(rs.getString(4));
        return feature;
    }
}
