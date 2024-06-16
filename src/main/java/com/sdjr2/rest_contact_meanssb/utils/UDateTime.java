package com.sdjr2.rest_contact_meanssb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * {@link UDateTime} class.
 * <p>
 * <strong>Utilities</strong> - Represents a class to handle methods on dates and time.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Utilities
 * @upgrade 24/06/16
 * @since 24/06/16
 */
public class UDateTime {

  private static final Logger LOG = LoggerFactory.getLogger(UDateTime.class);

  private UDateTime() {
    throw new IllegalStateException("Utility class");
  }

  public static LocalDate parseStringToLocalDate(final String value, final String format) {
    if (Objects.nonNull(value) && Objects.nonNull(format)) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
      return LocalDate.parse(value, formatter);
    }

    return null;
  }

  public static String parseLocalDateToString(final LocalDate value, final String format) {
    if (Objects.nonNull(value) && Objects.nonNull(format)) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
      return value.format(formatter);
    }

    return null;
  }

  public static String getTimestamp() {
    // Get the current time with the UTC time zone offset
    OffsetDateTime currentTime = OffsetDateTime.now();

    // Create a DateTimeFormatter for the desired format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(UConstants.S_FORMAT_TIMESTAMP_BACK);

    // Format the current time using the formatter
    return currentTime.format(formatter);
  }
}
