package com.longfor.itserver.common.helper;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class DataPermissionHelper {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String, String> loginNameMap;

    private static class DataPermissionHelperHolder {
        private static final DataPermissionHelper INSTANCE = new DataPermissionHelper() {
        };
    }

    private DataPermissionHelper() {

    }

    public static DataPermissionHelper getInstance() {
        return DataPermissionHelper.DataPermissionHelperHolder.INSTANCE;
    }

    private void reload(){
        loadCoderXml();
    }

    public boolean isShowAllData(String loginName){
        loadCoderXml();
        return loginNameMap.containsKey(loginName);
    }

    private void loadCoderXml() {
        SAXReader reader = new SAXReader();
        Document document;
        try {
            InputStream in = this.getClass().getResourceAsStream("/xml/dataPermission.xml");
            document = reader.read(in);
        } catch (Exception e) {
            logger.error("**** DataPermissionHelper -> loadCoderXml fail! ****", e);
            return;
        }

        /* new or reset */
        loginNameMap = new HashMap<String, String>();

        Element root = document.getRootElement();
        for (Iterator iterA = root.elementIterator(); iterA.hasNext(); ) {
            Element elementA = (Element) iterA.next();
            if (elementA != null && elementA.hasContent()) {
                if ("accounts".equals(elementA.getName())) {
                    // 获取节点 account
                    for (Iterator iterB = elementA.elementIterator(); iterB
                            .hasNext(); ) {
                        Element elementB = (Element) iterB.next();
                        if (elementB != null && "account".equals(elementB.getName())) {
                            Attribute attrB_loginName = elementB.attribute("loginName");

                            if(attrB_loginName == null
                                    || StringUtils.isBlank(attrB_loginName.getText())){
                                continue;
                            }
                            loginNameMap.put(attrB_loginName.getText(), attrB_loginName.getText());
                        }
                    }
                }
            }
        }
    }

}
