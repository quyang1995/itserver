/**
 * GetUnFinisherResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.longfor.itserver.esi.bpm.bpm;

public class GetUnFinisherResponse  implements java.io.Serializable {
    private NextPerson[] getUnFinisherResult;

    private String errorMessage;

    public GetUnFinisherResponse() {
    }

    public GetUnFinisherResponse(
           NextPerson[] getUnFinisherResult,
           String errorMessage) {
           this.getUnFinisherResult = getUnFinisherResult;
           this.errorMessage = errorMessage;
    }


    /**
     * Gets the getUnFinisherResult value for this GetUnFinisherResponse.
     * 
     * @return getUnFinisherResult
     */
    public NextPerson[] getGetUnFinisherResult() {
        return getUnFinisherResult;
    }


    /**
     * Sets the getUnFinisherResult value for this GetUnFinisherResponse.
     * 
     * @param getUnFinisherResult
     */
    public void setGetUnFinisherResult(NextPerson[] getUnFinisherResult) {
        this.getUnFinisherResult = getUnFinisherResult;
    }


    /**
     * Gets the errorMessage value for this GetUnFinisherResponse.
     * 
     * @return errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }


    /**
     * Sets the errorMessage value for this GetUnFinisherResponse.
     * 
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetUnFinisherResponse)) return false;
        GetUnFinisherResponse other = (GetUnFinisherResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getUnFinisherResult==null && other.getGetUnFinisherResult()==null) || 
             (this.getUnFinisherResult!=null &&
              java.util.Arrays.equals(this.getUnFinisherResult, other.getGetUnFinisherResult()))) &&
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
        if (getGetUnFinisherResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGetUnFinisherResult());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getGetUnFinisherResult(), i);
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
        new org.apache.axis.description.TypeDesc(GetUnFinisherResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetUnFinisherResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getUnFinisherResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetUnFinisherResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "NextPerson"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "NextPerson"));
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
