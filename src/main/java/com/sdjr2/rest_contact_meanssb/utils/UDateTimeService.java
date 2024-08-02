package com.sdjr2.rest_contact_meanssb.utils;

import com.sdjr2.rest_contact_meanssb.config.properties.GlobalProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * {@link UDateTimeService} class.
 * <p>
 * <strong>Utilities</strong> - Represents a class to handle methods on dates and time.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Utilities
 * @upgrade 24/06/16
 * @since 24/06/16
 */
@Service
@RequiredArgsConstructor
public class UDateTimeService {

	/**
	 * Global Properties about Logs, Constants, Regular Expression, etc
	 */
	private final GlobalProperties globalProperties;

	/* LocalDate */
	public LocalDate parseStringToLocalDate ( final String value, final String format ) {
		if ( Objects.nonNull( value ) && Objects.nonNull( format ) ) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern( format );
			return LocalDate.parse( value, formatter );
		}

		return null;
	}

	public String parseLocalDateToString ( final LocalDate value, final String format ) {
		if ( Objects.nonNull( value ) && Objects.nonNull( format ) ) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern( format );
			return value.format( formatter );
		}

		return null;
	}

	/* LocalDateTime */
	public LocalDateTime parseStringToLocalDateTime ( final String value, final String format ) {
		if ( Objects.nonNull( value ) && Objects.nonNull( format ) ) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern( format );
			return LocalDateTime.parse( value, formatter );
		}

		return null;
	}

	public LocalDateTime parseStringToLocalDateTimeAboutFrontend ( final String value ) {
		return this.parseStringToLocalDateTime( value, this.globalProperties.getFormatDateTimeFrontend() );
	}

	public LocalDateTime parseStringToLocalDateTimeAboutBackend ( final String value ) {
		return this.parseStringToLocalDateTime( value, this.globalProperties.getFormatDateTimeBackend() );
	}

	public String parseLocalDateTimeToString ( final LocalDateTime value, final String format ) {
		if ( Objects.nonNull( value ) && Objects.nonNull( format ) ) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern( format );
			return value.format( formatter );
		}

		return null;
	}

	public String parseLocalDateTimeToStringAboutFrontend ( final LocalDateTime value ) {
		return this.parseLocalDateTimeToString( value, this.globalProperties.getFormatDateTimeFrontend() );
	}

	public String getTimestamp () {
		// Get the current time with the UTC time zone offset
		OffsetDateTime currentTime = OffsetDateTime.now();

		// Create a DateTimeFormatter for the desired format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				this.globalProperties.getFormatTimestampBackend() );

		// Format the current time using the formatter
		return currentTime.format( formatter );
	}
}
