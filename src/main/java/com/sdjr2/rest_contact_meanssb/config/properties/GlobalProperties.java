package com.sdjr2.rest_contact_meanssb.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * {@link GlobalProperties} class.
 * <p>
 * <strong>Config - Properties</strong> - Global properties with logs, constants, regexp, etc.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Config
 * @upgrade 24/06/16
 * @since 24/06/16
 */
@Component
@PropertySource("classpath:/properties/global.properties")
@ConfigurationProperties
@Getter
public class GlobalProperties {

  // Logs
  private String configLogMsgBaseOk;
  private String configLogMsgBaseInfo;
  private String configLogMsgBaseError;

  // Constants
  private String configFormatDateFrontend;
  private String configFormatDateBackend;
  private String configFormatTimeBackend;
  private String configFormatDateTimeBackend;
  private String configFormatTimestampBackend;

  // RegExp - Generic
  private String configRegexId;
  private String configRegexNameGeneric;
  private String configRegexAdditionalInfo;
  // RegExp - Address
  private String configRegexAddressStreet;
  private String configRegexAddressNumber;
  private String configRegexAddressLetter;
  private String configRegexAddressTown;
  private String configRegexAddressCity;
  private String configRegexAddressCountry;
  private String configRegexAddressPostalCode;
  private String configRegexAddressCoordinates;
}
