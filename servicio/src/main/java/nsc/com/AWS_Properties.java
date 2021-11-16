package nsc.com;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class AWS_Properties {

    private Integer puerto;
	
	

}


