package com.sdjr2.rest_contact_meanssb.utils;

public class UConstants {

    private UConstants() {
        throw new IllegalStateException("Utility class");
    }

    // --- LOGS ---
    public static final String MSG_BASE_OK = "[OK] » ";
    public static final String MSG_BASE_INFO = "[INFO] » ";
    public static final String MSG_BASE_ERROR = "[ERROR] » ";
    public static final String MSG_BASE_REQUEST = "[REQ] » ";

    // --- FORMAT DATE ---
    public static final String S_FORMAT_DATE_FRONT = "dd/MM/yyyy";
    public static final String S_FORMAT_DATE_BACK = "yyyy-MM-dd";
    public static final String S_FORMAT_TIME_BACK = "HH:mm:ss";
    public static final String S_FORMAT_DATETIME_BACK = "yyyy-MM-dd HH:mm:ss";
    public static final String S_FORMAT_TIMESTAMP_BACK = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    // --- REG_EXP ---
    public static final String INDEX_REGEX = "^(\\d{1,8})";
    public static final String NAME_GENERIC_REGEX = "^[A-Z]{1}[a-zñA-Záéíóú\\s/]";
    public static final String ADDITIONAL_INFO_REGEX = "^[A-Z]{1}[a-zA-Záéíóú\\s\\.\\_\\-,;()¿?!¡=]{3,2500}";
    public static final String STREET_REGEX = UConstants.NAME_GENERIC_REGEX + "{2,120}";
    public static final String NUMBER_REGEX = "^[0-9]{1,5}$";
    public static final String LETTER_REGEX = "^[A-Z]{1,3}$";
    public static final String TOWN_REGEX = UConstants.NAME_GENERIC_REGEX + "{2,80}";
    public static final String CITY_REGEX = UConstants.NAME_GENERIC_REGEX + "{2,60}";
    public static final String COUNTRY_REGEX = UConstants.NAME_GENERIC_REGEX + "{2,40}";
    public static final String POSTAL_CODE_REGEX = "/^(?:0?[1-9]|[1-4]\\d|5[0-2])\\d{3}$/";
    public static final String LATITUDE_LONGITUDE_REGEX = "/^([\\-]?\\d{1,2}(?:[\\.\\,]\\d{5,8})?)$/";
}
