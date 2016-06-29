package hu.psprog.lcm.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hu.psprog.lcm.exception.LCMException;
import hu.psprog.lcm.model.Parameter;
import hu.psprog.lcm.service.ConfigManagerService;

@Controller
public class ConfigManagerController {

    @Autowired
    private ConfigManagerService configManagerService;
    
    @PostConstruct
    private void populateParameters() {
        
        try {
            this.parameters = configManagerService.getConfigParameters();
        } catch(LCMException exception) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", exception.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);           
        }
    }
    
    private List<Parameter> parameters;
    
    public List<Parameter> getParameters() {
        
        return parameters;
    }
    
    public void setParameters(List<Parameter> parameters) {
        
        this.parameters = parameters;
    }
    
    public void extend() {
        
        this.parameters.add(new Parameter());
    }
    
    public void remove(Parameter parameter) {
        
        this.parameters.remove(parameter);
    }

    public void saveConfig(ActionEvent actionEvent) {
        
        boolean success = configManagerService.writeConfigParameters(parameters);
        String statusMessage = success ? "Configuration successfully changed" : "Failed to save configuration";
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation finished", statusMessage);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
