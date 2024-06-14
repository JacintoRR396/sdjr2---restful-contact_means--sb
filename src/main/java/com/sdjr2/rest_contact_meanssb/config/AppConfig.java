package com.sdjr2.rest_contact_meanssb.config;

import com.sdjr2.rest_contact_meanssb.repositories.AddressRepository;
import com.sdjr2.rest_contact_meanssb.repositories.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.repositories.mock.AddressRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.Resource;

/**
 * {@link AppConfig} class.
 * <p>
 * <strong>Config</strong> - Global configuration about application.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Config
 * @upgrade 24/06/13
 * @since 24/06/13
 */
@Configuration
@PropertySources({
  @PropertySource("classpath:/properties/config.properties"),
  @PropertySource("classpath:/properties/messages.properties")
})
public class AppConfig {

  @Value("classpath:/database/json/address.json")
  private Resource resourceAddressJson;

  @Bean
  AddressRepository<AddressEntity, Integer> addressRepository(){
    return new AddressRepositoryImpl(resourceAddressJson);
  }
}
