package com.longfor.itserver.common.Datatable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author mayee
 * Created on 2017/2/10 下午4:35
 *
 * @version v1.0
 */
public class Item implements Serializable {

    private static final long serialVersionUID = 6769848002064163039L;
    private String name;
    private String text;
    private String defText;

    public Item(String name, String text, String defText) {
        this.name = name;
        this.text = text;
        this.defText = defText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDefText() {
        return defText;
    }

    public void setDefText(String defText) {
        this.defText = defText;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
