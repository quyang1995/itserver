/**
 * InstanceStageModel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.longfor.itserver.esi.bpm.bpm;

public class InstanceStageModel  implements java.io.Serializable {
    private String code;

    private String comments;

    private String instanceid;

    private String label;

    private String laststageid;

    private String persons;

    private int rank;

    private String stageStatu;

    private String stageid;

    private String stagename;

    public InstanceStageModel() {
    }

    public InstanceStageModel(
           String code,
           String comments,
           String instanceid,
           String label,
           String laststageid,
           String persons,
           int rank,
           String stageStatu,
           String stageid,
           String stagename) {
           this.code = code;
           this.comments = comments;
           this.instanceid = instanceid;
           this.label = label;
           this.laststageid = laststageid;
           this.persons = persons;
           this.rank = rank;
           this.stageStatu = stageStatu;
           this.stageid = stageid;
           this.stagename = stagename;
    }


    /**
     * Gets the code value for this InstanceStageModel.
     * 
     * @return code
     */
    public String getCode() {
        return code;
    }


    /**
     * Sets the code value for this InstanceStageModel.
     * 
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * Gets the comments value for this InstanceStageModel.
     * 
     * @return comments
     */
    public String getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this InstanceStageModel.
     * 
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }


    /**
     * Gets the instanceid value for this InstanceStageModel.
     * 
     * @return instanceid
     */
    public String getInstanceid() {
        return instanceid;
    }


    /**
     * Sets the instanceid value for this InstanceStageModel.
     * 
     * @param instanceid
     */
    public void setInstanceid(String instanceid) {
        this.instanceid = instanceid;
    }


    /**
     * Gets the label value for this InstanceStageModel.
     * 
     * @return label
     */
    public String getLabel() {
        return label;
    }


    /**
     * Sets the label value for this InstanceStageModel.
     * 
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }


    /**
     * Gets the laststageid value for this InstanceStageModel.
     * 
     * @return laststageid
     */
    public String getLaststageid() {
        return laststageid;
    }


    /**
     * Sets the laststageid value for this InstanceStageModel.
     * 
     * @param laststageid
     */
    public void setLaststageid(String laststageid) {
        this.laststageid = laststageid;
    }


    /**
     * Gets the persons value for this InstanceStageModel.
     * 
     * @return persons
     */
    public String getPersons() {
        return persons;
    }


    /**
     * Sets the persons value for this InstanceStageModel.
     * 
     * @param persons
     */
    public void setPersons(String persons) {
        this.persons = persons;
    }


    /**
     * Gets the rank value for this InstanceStageModel.
     * 
     * @return rank
     */
    public int getRank() {
        return rank;
    }


    /**
     * Sets the rank value for this InstanceStageModel.
     * 
     * @param rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }


    /**
     * Gets the stageStatu value for this InstanceStageModel.
     * 
     * @return stageStatu
     */
    public String getStageStatu() {
        return stageStatu;
    }


    /**
     * Sets the stageStatu value for this InstanceStageModel.
     * 
     * @param stageStatu
     */
    public void setStageStatu(String stageStatu) {
        this.stageStatu = stageStatu;
    }


    /**
     * Gets the stageid value for this InstanceStageModel.
     * 
     * @return stageid
     */
    public String getStageid() {
        return stageid;
    }


    /**
     * Sets the stageid value for this InstanceStageModel.
     * 
     * @param stageid
     */
    public void setStageid(String stageid) {
        this.stageid = stageid;
    }


    /**
     * Gets the stagename value for this InstanceStageModel.
     * 
     * @return stagename
     */
    public String getStagename() {
        return stagename;
    }


    /**
     * Sets the stagename value for this InstanceStageModel.
     * 
     * @param stagename
     */
    public void setStagename(String stagename) {
        this.stagename = stagename;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof InstanceStageModel)) return false;
        InstanceStageModel other = (InstanceStageModel) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.comments==null && other.getComments()==null) || 
             (this.comments!=null &&
              this.comments.equals(other.getComments()))) &&
            ((this.instanceid==null && other.getInstanceid()==null) || 
             (this.instanceid!=null &&
              this.instanceid.equals(other.getInstanceid()))) &&
            ((this.label==null && other.getLabel()==null) || 
             (this.label!=null &&
              this.label.equals(other.getLabel()))) &&
            ((this.laststageid==null && other.getLaststageid()==null) || 
             (this.laststageid!=null &&
              this.laststageid.equals(other.getLaststageid()))) &&
            ((this.persons==null && other.getPersons()==null) || 
             (this.persons!=null &&
              this.persons.equals(other.getPersons()))) &&
            this.rank == other.getRank() &&
            ((this.stageStatu==null && other.getStageStatu()==null) || 
             (this.stageStatu!=null &&
              this.stageStatu.equals(other.getStageStatu()))) &&
            ((this.stageid==null && other.getStageid()==null) || 
             (this.stageid!=null &&
              this.stageid.equals(other.getStageid()))) &&
            ((this.stagename==null && other.getStagename()==null) || 
             (this.stagename!=null &&
              this.stagename.equals(other.getStagename())));
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
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getComments() != null) {
            _hashCode += getComments().hashCode();
        }
        if (getInstanceid() != null) {
            _hashCode += getInstanceid().hashCode();
        }
        if (getLabel() != null) {
            _hashCode += getLabel().hashCode();
        }
        if (getLaststageid() != null) {
            _hashCode += getLaststageid().hashCode();
        }
        if (getPersons() != null) {
            _hashCode += getPersons().hashCode();
        }
        _hashCode += getRank();
        if (getStageStatu() != null) {
            _hashCode += getStageStatu().hashCode();
        }
        if (getStageid() != null) {
            _hashCode += getStageid().hashCode();
        }
        if (getStagename() != null) {
            _hashCode += getStagename().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InstanceStageModel.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "InstanceStageModel"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "comments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instanceid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "instanceid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "label"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("laststageid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "laststageid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("persons");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "persons"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rank");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "rank"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stageStatu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "stageStatu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stageid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "stageid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stagename");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "stagename"));
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
