package com.sdjr2.rest_contact_meanssb.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
@Configuration
@ConfigurationProperties(prefix = "config")
@Data
public class GlobalProperties {

	// Logs
	private String logMsgBaseOk;
	private String logMsgBaseInfo;
	private String logMsgBaseError;

	// Constants
	private String formatDateFrontend;
	private String formatDateBackend;
	private String formatTimeBackend;
	private String formatDateTimeBackend;
	private String formatTimestampBackend;

	// RegExp - Generic
	private String regexId;
	private String regexNameGeneric;
	private String regexAdditionalInfo;
	// RegExp - Address
	private String regexAddressStreet;
	private String regexAddressNumber;
	private String regexAddressLetter;
	private String regexAddressTown;
	private String regexAddressCity;
	private String regexAddressCountry;
	private String regexAddressPostalCode;
	private String regexAddressCoordinates;
}
