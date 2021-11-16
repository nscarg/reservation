package nsc.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * ConfigurablePropertyPlaceholder takes instructions which SystemProperty
 * contains the path to the propertyfile to load.
 *
 * @author Gabe Kaelin
 * 
 */
public class ConfigurablePropertyPlaceholder extends PropertyPlaceholderConfigurer {


    private String propertyLocationSystemProperty;
    private String defaultPropertyFileName;


    public String getPropertyLocationSystemProperty() {
        return propertyLocationSystemProperty;
    }

    public void setPropertyLocationSystemProperty(String propertyLocationSystemProperty) {
        this.propertyLocationSystemProperty = propertyLocationSystemProperty;
    }

    public String getDefaultPropertyFileName() {
        return defaultPropertyFileName;
    }

    public void setDefaultPropertyFileName(String defaultPropertyFileName) {
        this.defaultPropertyFileName = defaultPropertyFileName;
    }

    /**
     * Overridden to fill the location with the path from the {@link #propertyLocationSystemProperty}
     *
     * @param props propeties instance to fill
     * @throws IOException
     */

    @Override
    protected void loadProperties(Properties props) throws IOException {
        Resource location = null;
        if(StringUtils.isNotEmpty(propertyLocationSystemProperty)){

            String propertyFilePath = System.getProperties().getProperty(propertyLocationSystemProperty);
            StringBuilder pathBuilder = new StringBuilder(propertyFilePath);

            if(StringUtils.isNotEmpty(defaultPropertyFileName) && !propertyFilePath.endsWith(defaultPropertyFileName)){
                pathBuilder.append("/").append(defaultPropertyFileName);
            }

            location = new FileSystemResource(pathBuilder.toString());
        }

        setLocation(location);
        super.loadProperties(props);
    }
}