package hu.psprog.lcm;

import java.io.File;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ApplicationConfiguration {

    private static final String JNDI_LEAFLET_CONFIG_LOCATION = "java:comp/env/leafletAppConfig";
    
    @Bean
    public File configFile() throws NamingException {
        
        return InitialContext.doLookup(JNDI_LEAFLET_CONFIG_LOCATION);
    }
}
