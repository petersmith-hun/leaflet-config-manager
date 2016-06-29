package hu.psprog.lcm.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "properties")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParameterListWrapper {

    @XmlElement(name = "entry")
    private List<Parameter> parameters;
    
    public ParameterListWrapper() {
        
    }
    
    public ParameterListWrapper(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
