package hu.psprog.lcm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.psprog.lcm.exception.LCMException;
import hu.psprog.lcm.model.Parameter;
import hu.psprog.lcm.model.ParameterListWrapper;

@Service
public class ConfigManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigManagerService.class);
    
    @Autowired
    private File configFile;
    
    public List<Parameter> getConfigParameters() throws LCMException {
        
        return readProperties().entrySet().stream()
                .map(e -> new Parameter(e.getKey(), e.getValue()))
                .sorted()
                .collect(Collectors.toList());
    }
    
    public boolean writeConfigParameters(List<Parameter> parameters) {
        
        try(FileOutputStream outputStream = new FileOutputStream(configFile)) {
            
            JAXBContext jaxbContext = JAXBContext.newInstance(ParameterListWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">");
            jaxbMarshaller.marshal(new ParameterListWrapper(parameters), outputStream);
            
            return true;
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return false;
    }
    
    private Properties readProperties() throws LCMException {
        
        Properties properties = new Properties();
        
        try {
            properties.loadFromXML(new FileInputStream(configFile));
        } catch (InvalidPropertiesFormatException e) {
             LOGGER.error(e.getMessage(), e);
             throw new LCMException("Invalid config XML format");
             
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LCMException("Config XML file not found");
            
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LCMException("IOException occured, check log");
        }
        
        return properties;
    }
}
