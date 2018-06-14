package com.longfor.itserver.spring.configurer;

import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.helper.JoddHelper;
import jodd.props.Props;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.Properties;

/**
 * 将配置文件加载到spring中
 *
 * @author mayee
 * Created on 2017/3/30 10:33
 *
 * @version v1.0
 */
public final class JoddPropsConfigurer extends PropertyPlaceholderConfigurer {
    private final Logger logger = LoggerFactory.getLogger(JoddPropsConfigurer.class);

    protected void processProperties(
            ConfigurableListableBeanFactory beanFactory, Properties props)
            throws BeansException {
        Properties propertiesInit = new Properties();
        try {
            propertiesInit = super.mergeProperties();
        } catch (IOException e) {
            logger.error("**** [init.properties] failed to load! ****", e);
        }

        Props joddProps = new Props();
        ResourceLoader loader = new DefaultResourceLoader();
        String[] propertiesFileList = getPropertiesFileList(propertiesInit.getProperty("init.list"));
        for (String file : propertiesFileList) {
            logger.debug("----> Loading config :" + file);
            Resource resourceApp = loader.getResource(file);
            if (resourceApp.exists()) {
                try {
//                    p.load(new File(URLUtil.getClassPath(this.getClass())+"/a.properties"));
                    joddProps.load(resourceApp.getInputStream(), ConfigConsts.UTF8);
                    logger.debug("----> Succeeded[" + file + "]");
                } catch (IOException e) {
                    logger.error("**** [" + file + "] failed to load! ****", e);
                }
            } else {
                logger.error("**** [" + file + "] does not exist! ****");
            }
        }

        String jdbc_url = "jdbc.url";
        String jdbc_username = "jdbc.username";
        String jdbc_password = "jdbc.password";
        String jdbc_initial_size = "jdbc.initial.size";
        String jdbc_max_active = "jdbc.max.active";

        String web_project_url = "web.project.url";

        Properties properties = new Properties();
        properties.setProperty(jdbc_url, joddProps.getValue(jdbc_url));
        properties.setProperty(jdbc_username, joddProps.getValue(jdbc_username));
        properties.setProperty(jdbc_password, joddProps.getValue(jdbc_password));
        properties.setProperty(jdbc_max_active, joddProps.getValue(jdbc_max_active));
        properties.setProperty(jdbc_initial_size, joddProps.getValue(jdbc_initial_size));

        properties.setProperty("redis.pass", joddProps.getValue("redis.pass"));
        properties.setProperty("redis.pool.maxTotal", joddProps.getValue("redis.pool.maxTotal"));
        properties.setProperty("redis.pool.maxIdle", joddProps.getValue("redis.pool.maxIdle"));
        properties.setProperty("redis.pool.maxWaitMillis", joddProps.getValue("redis.pool.maxWaitMillis"));
        properties.setProperty("redis.pool.testOnBorrow", joddProps.getValue("redis.pool.testOnBorrow"));
        properties.setProperty("redis.ip", joddProps.getValue("redis.ip"));
        properties.setProperty("redis.port", joddProps.getValue("redis.port"));
        properties.setProperty("redis.database", joddProps.getValue("redis.database"));
        properties.setProperty("web.project.url", joddProps.getValue(web_project_url));

        properties.setProperty("mq.ONSAddr", joddProps.getValue("mq.ONSAddr"));
        properties.setProperty("mq.accessKey", joddProps.getValue("mq.accessKey"));
        properties.setProperty("mq.secretKey", joddProps.getValue("mq.secretKey"));

/*        properties.setProperty("eds.url", joddProps.getValue("eds.url"));
        properties.setProperty("eds.token", joddProps.getValue("eds.token"));
        properties.setProperty("ads.url", joddProps.getValue("ads.url"));
        properties.setProperty("ads.token", joddProps.getValue("ads.token"));*/


        JoddHelper.getInstance().setJoddProps(joddProps);
        super.processProperties(beanFactory, properties);
    }

    private String[] getPropertiesFileList(String value) {
        return StringUtils.split(value, ",");
    }
}
