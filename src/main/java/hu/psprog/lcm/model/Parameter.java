package hu.psprog.lcm.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class Parameter implements Comparable<Parameter> {

    @XmlAttribute(name = "key")
    private String key;
    
    @XmlValue
    private String value;

    public Parameter() {
        // Serializable
    }

    public Parameter(Object key, Object value) {
        super();
        this.key = key.toString();
        this.value = value.toString();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "Parameter [key=" + key + ", value=" + value + "]";
    }

    @Override
    public int compareTo(Parameter o) {
        
        return this.key.compareTo(o.key);
    }
}
