/**
 * AddConsult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.longfor.itserver.esi.bpm.bpm;

public class AddConsult  implements java.io.Serializable {
    private String workItemId;

    private String itemComment;

    public AddConsult() {
    }

    public AddConsult(
           String workItemId,
           String itemComment) {
           this.workItemId = workItemId;
           this.itemComment = itemComment;
    }


    /**
     * Gets the workItemId value for this AddConsult.
     * 
     * @return workItemId
     */
    public String getWorkItemId() {
        return workItemId;
    }


    /**
     * Sets the workItemId value for this AddConsult.
     * 
     * @param workItemId
     */
    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }


    /**
     * Gets the itemComment value for this AddConsult.
     * 
     * @return itemComment
     */
    public String getItemComment() {
        return itemComment;
    }


    /**
     * Sets the itemComment value for this AddConsult.
     * 
     * @param itemComment
     */
    public void setItemComment(String itemComment) {
        this.itemComment = itemComment;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof AddConsult)) return false;
        AddConsult other = (AddConsult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.workItemId==null && other.getWorkItemId()==null) || 
             (this.workItemId!=null &&
              this.workItemId.equals(other.getWorkItemId()))) &&
            ((this.itemComment==null && other.getItemComment()==null) || 
             (this.itemComment!=null &&
              this.itemComment.equals(other.getItemComment())));
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
        if (getWorkItemId() != null) {
            _hashCode += getWorkItemId().hashCode();
        }
        if (getItemComment() != null) {
            _hashCode += getItemComment().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddConsult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">AddConsult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workItemId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "workItemId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itemComment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "itemComment"));
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
