package com.longfor.itserver.common.helper;

import com.longfor.itserver.common.constant.ConfigConsts;
import jodd.props.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author mayee
 * Created on 2017/2/9 下午4:07
 *
 * @version v1.0
 */
public abstract class JoddHelper {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String applicationConfigFileClassPath;
    private Props props;

    private static class JoddHelperHolder {
        private static final JoddHelper INSTANCE = new JoddHelper() {
        };
    }

    private JoddHelper() {
    }

    public static JoddHelper getInstance() {
        return JoddHelperHolder.INSTANCE;
    }

    public void setJoddProps(Props props) {
        this.props = props;
    }

    public Props getJoddProps() {
        return this.props != null ? this.props : initJoddProps();
    }

    private Props initJoddProps() {
        Props p = new Props();
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource_app = loader.getResource(this.applicationConfigFileClassPath);
        if (resource_app.exists()) {
            try {
                p.load(resource_app.getInputStream(), ConfigConsts.UTF8);
            } catch (IOException e) {
                logger.error("**** JoddHelper -> initJoddProps fail! ****", e);
            }
        }
        return p;
    }

    public InputStream read(String path) {
        return this.getClass().getResourceAsStream(path);
    }

}
