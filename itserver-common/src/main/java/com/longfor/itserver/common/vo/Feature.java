package com.longfor.itserver.common.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomDateSerializer;
import net.mayee.commons.CustomFullDateSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Feature implements Serializable {
    private static final long serialVersionUID = -653566611207069924L;
    private BigDecimal id;

    private String featureName;

    private String featureType;

    private String userKey;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureType() {
        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
}