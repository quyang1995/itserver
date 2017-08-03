package com.longfor.itserver.common.Datatable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mayee
 * Created on 2017/2/10 下午4:34
 *
 * @version v1.0
 */
public class Datatable {

    private String id;
    private String name;

    private Map<String, String> propertyMap;

    public Datatable(String id, String name){
        this.id = id;
        this.name = name;
        propertyMap = new HashMap<String, String>();
    }

    public Map<String, String> getPropertyMap() {
        return propertyMap;
    }

    public String getPropertyValue(String key){
        return propertyMap.get(key);
    }

    public void setProperty(String key, String value){
        propertyMap.put(key, value);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
