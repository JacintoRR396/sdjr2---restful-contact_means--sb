package com.sdjr2.rest_contact_meanssb.utils;

public class UConstants {

	private UConstants () {
		throw new IllegalStateException( "Utility class" );
	}

	// --- GENERIC ---
	public static final String NOT_APPLY = "N/A";

	// --- LOGS ---
	public static final String MSG_BASE_OK = "[OK] » ";
	public static final String MSG_BASE_INFO = "[INFO] » ";
	public static final String MSG_BASE_ERROR = "[ERROR] » ";
	public static final String MSG_BASE_REQUEST = "[REQ] » ";

	// --- FORMAT DATE ---
	public static final String S_FORMAT_DATE_FRONT = "dd/MM/yyyy";
	public static final String S_FORMAT_DATE_BACK = "yyyy-MM-dd";
	public static final String S_FORMAT_TIME_FRONT = "HH:mm:ss";
	public static final String S_FORMAT_TIME_BACK = "HH:mm:ss";
	public static final String S_FORMAT_DATETIME_FRONT = "dd/MM/yyyy HH:mm:ss";
	public static final String S_FORMAT_DATETIME_BACK = "yyyy-MM-dd HH:mm:ss";
	public static final String S_FORMAT_TIMESTAMP_BACK = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

	// --- REG_EXP ---
	public static final String REGEX_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	public static final String REGEX_ROLE = "ROLE_[A-Z]{1,15}";

	public static final String REGEX_NAME_GENERIC = "^[A-Z]{1}[a-zñA-Záéíóú\\s/]";
	public static final String ADDITIONAL_INFO_REGEX = "^[A-Z]{1}[a-zñA-Záéíóú\\s\\.\\_\\-,;()¿?!¡=\\d]{3,2500}";
	public static final String REGEX_STREET = UConstants.REGEX_NAME_GENERIC + "{2,120}";
	public static final String REGEX_NUMBER = "^[0-9]{1,5}$";
	public static final String REGEX_LETTER = "^[A-Z\\/]{1,3}$";
	public static final String REGEX_TOWN = UConstants.REGEX_NAME_GENERIC + "{2,80}";
	public static final String REGEX_CITY = UConstants.REGEX_NAME_GENERIC + "{2,60}";
	public static final String REGEX_COUNTRY = UConstants.REGEX_NAME_GENERIC + "{2,40}";
	public static final String REGEX_POSTAL_CODE = "^(?:0?[1-9]|[1-4]\\d|5[0-2])\\d{3}$";
	public static final String REGEX_LATITUDE_LONGITUDE = "^([\\-]?\\d{1,2}(?:[\\.\\,]\\d{5,8})?)$";
}
