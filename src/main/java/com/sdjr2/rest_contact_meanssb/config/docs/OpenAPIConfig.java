package com.sdjr2.rest_contact_meanssb.config.docs;

import com.sdjr2.rest_contact_meanssb.config.properties.OpenApiProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * {@link OpenAPIConfig} class.
 * <p>
 * <strong>Config</strong> - OpenApi configuration about application.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Config
 * @upgrade 24/08/19
 * @since 24/08/19
 */
@Configuration
public class OpenAPIConfig {

  @Autowired
  private OpenApiProperties openApiProperties;

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(this.openApiProperties.getMicro().get("urlDev"));
    devServer.setDescription("Server URL in Development environment");

    Server preServer = new Server();
    preServer.setUrl(this.openApiProperties.getMicro().get("urlPre"));
    preServer.setDescription("Server URL in Preproduction environment");

    Server prodServer = new Server();
    prodServer.setUrl(this.openApiProperties.getMicro().get("urlProd"));
    prodServer.setDescription("Server URL in Production environment");

    Contact contact = new Contact();
    contact.setName(this.openApiProperties.getAuthor().get("name"));
    contact.setEmail(this.openApiProperties.getAuthor().get("email"));
    contact.setUrl(this.openApiProperties.getAuthor().get("url"));

    License mitLicense = new License()
       .name(this.openApiProperties.getLicense().get("name"))
       .url(this.openApiProperties.getLicense().get("url"));

    Info info = new Info()
       .title(this.openApiProperties.getTitle())
       .version(this.openApiProperties.getVersion())
       .description(this.openApiProperties.getDescription())
       .termsOfService(this.openApiProperties.getTermsOfService())
       .license(mitLicense)
       .contact(contact);

    return new OpenAPI().info(info).servers(List.of(devServer, preServer, prodServer));
  }
}
