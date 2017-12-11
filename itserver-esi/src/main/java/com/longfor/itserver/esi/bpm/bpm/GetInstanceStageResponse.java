/**
 * GetInstanceStageResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.longfor.itserver.esi.bpm.bpm;

public class GetInstanceStageResponse  implements java.io.Serializable {
    private InstanceStageModel[] getInstanceStageResult;

    private String errorMessage;

    public GetInstanceStageResponse() {
    }

    public GetInstanceStageResponse(
           InstanceStageModel[] getInstanceStageResult,
           String errorMessage) {
           this.getInstanceStageResult = getInstanceStageResult;
           this.errorMessage = errorMessage;
    }


    /**
     * Gets the getInstanceStageResult value for this GetInstanceStageResponse.
     * 
     * @return getInstanceStageResult
     */
    public InstanceStageModel[] getGetInstanceStageResult() {
        return getInstanceStageResult;
    }


    /**
     * Sets the getInstanceStageResult value for this GetInstanceStageResponse.
     * 
     * @param getInstanceStageResult
     */
    public void setGetInstanceStageResult(InstanceStageModel[] getInstanceStageResult) {
        this.getInstanceStageResult = getInstanceStageResult;
    }


    /**
     * Gets the errorMessage value for this GetInstanceStageResponse.
     * 
     * @return errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }


    /**
     * Sets the errorMessage value for this GetInstanceStageResponse.
     * 
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetInstanceStageResponse)) return false;
        GetInstanceStageResponse other = (GetInstanceStageResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getInstanceStageResult==null && other.getGetInstanceStageResult()==null) || 
             (this.getInstanceStageResult!=null &&
              java.util.Arrays.equals(this.getInstanceStageResult, other.getGetInstanceStageResult()))) &&
            ((this.errorMessage==null && other.getErrorMessage()==null) || 
             (this.errorMessage!=null &&
              this.errorMessage.equals(other.getErrorMessage())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getGetInstanceStageResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGetInstanceStageResult());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getGetInstanceStageResult(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getErrorMessage() != null) {
            _hashCode += getErrorMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetInstanceStageResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetInstanceStageResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getInstanceStageResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetInstanceStageResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "InstanceStageModel"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "InstanceStageModel"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "errorMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
