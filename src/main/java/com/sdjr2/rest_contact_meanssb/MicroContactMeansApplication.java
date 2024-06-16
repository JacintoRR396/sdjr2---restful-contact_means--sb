package com.sdjr2.rest_contact_meanssb;

import com.sdjr2.rest_contact_meanssb.config.properties.GlobalProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ GlobalProperties.class })
public class MicroContactMeansApplication {

	public static void main ( String[] args ) {
		SpringApplication.run( MicroContactMeansApplication.class, args );
	}
}
