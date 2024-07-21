package com.sdjr2.rest_contact_meanssb;

import com.sdjr2.rest_contact_meanssb.config.properties.GlobalProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableConfigurationProperties({ GlobalProperties.class })
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class MicroContactMeansApplication {

	public static void main ( String[] args ) {
		SpringApplication.run( MicroContactMeansApplication.class, args );
	}
}
