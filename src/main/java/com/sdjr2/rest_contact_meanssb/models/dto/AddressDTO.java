package com.sdjr2.rest_contact_meanssb.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdjr2.sb.library_commons.models.dto.BaseDTO;
import com.sdjr2.sb.library_commons.utils.UConstants;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;

/**
 * {@link AddressDTO} class.
 * <p>
 * <strong>DTO</strong> - Represents an Address in the Request / Response, implements to
 * {@link BaseDTO}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category DTO
 * @upgrade 24/08/16
 * @since 23/06/11
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO implements BaseDTO, Comparable<AddressDTO>, Serializable {

	@Serial
	private static final long serialVersionUID = -6649157406633205057L;

	public static final String ATTR_ID = "id";
	public static final String ATTR_STREET = "street";
	public static final String ATTR_NUMBER = "number";
	public static final String ATTR_LETTER = "letter";
	public static final String ATTR_TOWN = "town";
	public static final String ATTR_CITY = "city";
	public static final String ATTR_COUNTRY = "country";
	public static final String ATTR_POSTAL_CODE = "postalCode";
	public static final String ATTR_LONGITUDE = "longitude";
	public static final String ATTR_LATITUDE = "latitude";
	public static final String ATTR_ADDITIONAL_INFO = "additionalInfo";

	/* VARIABLES */
	@PositiveOrZero
	@Digits(integer = 8, fraction = 0)
	private Long id;

	@NotNull
	@Pattern(regexp = UConstants.REGEX_STREET)
	private String street;

	@NotNull
	@Positive
	@Digits(integer = 5, fraction = 0)
	private Integer number;

	@Pattern(regexp = UConstants.REGEX_LETTER)
	private String letter = UConstants.NOT_APPLY;

	@Pattern(regexp = UConstants.REGEX_TOWN)
	private String town;

	@Pattern(regexp = UConstants.REGEX_CITY)
	private String city;

	@Pattern(regexp = UConstants.REGEX_COUNTRY)
	private String country;

	@NotNull
	@PositiveOrZero
	@Digits(integer = 5, fraction = 0)
	@JsonProperty(ATTR_POSTAL_CODE)
	private Integer postalCode;

	@Pattern(regexp = UConstants.REGEX_LATITUDE_LONGITUDE)
	private String longitude;

	@Pattern(regexp = UConstants.REGEX_LATITUDE_LONGITUDE)
	private String latitude;

	@Pattern(regexp = UConstants.REGEX_ADDITIONAL_INFO)
	@JsonProperty(ATTR_ADDITIONAL_INFO)
	private String additionalInfo;

	/* METHODS OF INSTANCE */
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.id.hashCode();
		result = prime * result + this.street.hashCode();
		result = prime * result + this.number.hashCode();
		result = prime * result + this.letter.hashCode();
		result = prime * result + this.postalCode.hashCode();
		return result;
	}

	@Override
	public boolean equals ( final Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( !( obj instanceof AddressDTO other ) ) {
			return false;
		}
		return this.id.equals( other.getId() )
				&& this.street.equals( other.getStreet() )
				&& this.number.intValue() == other.getNumber().intValue()
				&& this.letter.equals( other.getLetter() )
				&& this.postalCode.equals( other.getPostalCode() );
	}

	@Override
	public String toString () {
		return BaseDTO.toJsonStr( this );
	}

	@Override
	public int compareTo ( final AddressDTO obj ) {
		// Street, Number, Letter & PostalCode
		int res = this.getPostalCode().compareTo( obj.getPostalCode() );
		if ( res == 0 ) {
			res = this.getStreet().compareTo( obj.getStreet() );
			if ( res == 0 ) {
				res = this.getNumber().compareTo( obj.getNumber() );
				if ( res == 0 ) {
					res = this.getLetter().compareTo( obj.getLetter() );
				}
			}
		}

		return res;
	}

	/* METHODS OF CLASSES */
	public static AddressDTO valueOf ( final AddressDTO obj ) {
		return new AddressDTO( obj.getId(), obj.getStreet(), obj.getNumber(), obj.getLetter(), obj.getTown(), obj.getCity(),
				obj.getCountry(), obj.getPostalCode(), obj.getLongitude(), obj.getLatitude(), obj.getAdditionalInfo() );
	}

	public static final Comparator<AddressDTO> comparatorTown =
			Comparator.comparing( ( AddressDTO obj ) -> obj.getTown().toUpperCase() );
	public static final Comparator<AddressDTO> comparatorCity =
			Comparator.comparing( ( AddressDTO obj ) -> obj.getCity().toUpperCase() );
	public static final Comparator<AddressDTO> comparatorPostalCode = Comparator.comparing( AddressDTO::getPostalCode );
}
