package com.sdjr2.rest_contact_meanssb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UMethods {

    private static Logger LOG = LoggerFactory.getLogger(UMethods.class);

    public static String info(final String nameClass, final String nameMethod, final String msg) {
        return UConstants.MSG_BASE_INFO + nameClass + " : " + nameMethod + "() » " + msg;
    }

    public static String error(final String nameClass, final String nameMethod, final String msg) {
        return UConstants.MSG_BASE_ERROR + nameClass + " : " + nameMethod + "() » " + msg;
    }

    public static boolean isValidDateFromString(final String value, final Pattern pattern) {
        Boolean bRes = false;
        final Matcher oMatcher = pattern.matcher(value);
        bRes = oMatcher.matches();
        final LocalDate oLocalDate = UMethods.parseStringToLocalDate(value, UConstants.S_FORMAT_DATE_FRONT);
        if (bRes && (oLocalDate != null)) {
            bRes = true;
        } else {
            UMethods.LOG.error("\t La Fecha es posterior al día de hoy o no cumple el patrón '" + UConstants.S_FORMAT_DATE_FRONT + "'.");
            bRes = false;
        }
        return bRes;
    }

    public static LocalDate parseStringToLocalDate(final String value, final String formatter) {
        final DateTimeFormatter oDTFormat = DateTimeFormatter.ofPattern(formatter);
        LocalDate oLocalDate = LocalDate.parse(value, oDTFormat);
        if (oLocalDate.isAfter(LocalDate.now())) {
            UMethods.LOG.error("\t La fecha {} es posterior a {}.", oLocalDate, LocalDate.now());
            oLocalDate = null;
        }
        return oLocalDate;
    }

    public static String parseLocalDateToString(final LocalDate value, final String formatter) {
        String sDate = "";
        final DateTimeFormatter oDTFormat = DateTimeFormatter.ofPattern(formatter);
        try {
            sDate = oDTFormat.format(value);
        } catch (final NullPointerException ex) {
            UMethods.LOG.error("\t NO se ha podido Formatear la Fecha '" + value + "' a Cadena.");
        }
        return sDate;
    }

    public static String capitalizeWord(final String sWord) {
        String sRes = "";
        sRes += Character.toUpperCase(sWord.charAt(0));
        for (int i = 1; i < sWord.length(); i++) {
            sRes += Character.toLowerCase(sWord.charAt(i));
        }
        return sRes;
    }

    private UMethods() {
        throw new IllegalStateException("Utility class");
    }

}
