package com.sdjr2.rest_contact_meanssb.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdjr2.rest_contact_meanssb.models.validations.ContactExistsById;
import com.sdjr2.sb.library_commons.models.dto.BaseDTO;
import com.sdjr2.sb.library_commons.utils.UConstants;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;

/**
 * {@link ContactDTO} class.
 * <p>
 * <strong>DTO</strong> - Represents a Contact in the Request / Response, implements to
 * {@link BaseDTO}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category DTO
 * @upgrade 24/08/13
 * @since 24/08/12
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDTO implements BaseDTO, Comparable<ContactDTO>, Serializable {

	@Serial
	private static final long serialVersionUID = 3588857414605043219L;

	public static final String ATTR_ID = "id";
	public static final String ATTR_EMAIL = "email";
	public static final String ATTR_PHONE_MOBILE = "phoneMobile";
	public static final String ATTR_PHONE_HOME = "phoneHome";

	/* VARIABLES */
	@PositiveOrZero
	@Digits(integer = 8, fraction = 0)
	@ContactExistsById
	private Long id;

	@NotNull
	@Pattern(regexp = UConstants.REGEX_EMAIL)
	private String email;

	@NotNull
	@Pattern(regexp = UConstants.REGEX_PHONE)
	private String phoneMobile;

	@Pattern(regexp = UConstants.REGEX_PHONE)
	private String phoneHome;

	/* METHODS OF INSTANCE */
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.id.hashCode();
		result = prime * result + this.email.hashCode();
		return result;
	}

	@Override
	public boolean equals ( final Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( !( obj instanceof ContactDTO other ) ) {
			return false;
		}
		return this.id.equals( other.getId() )
				&& this.email.equals( other.getEmail() );
	}

	@Override
	public String toString () {
		final StringBuilder res = new StringBuilder(
				"The DTO '" + this.getClass().getSimpleName() + "' contains the attributes: \n" );
		res.append( " - Id » " ).append( this.id ).append( ".\n" );
		res.append( " - Email » " ).append( this.email ).append( ".\n" );
		res.append( " - Phone mobile » " ).append( this.phoneMobile ).append( ".\n" );
		res.append( " - Phone home » " ).append( this.phoneHome ).append( ".\n" );

		return res.toString();
	}

	@Override
	public int compareTo ( final ContactDTO obj ) {
		// Email
		return this.getEmail().compareTo( obj.getEmail() );
	}

	/* METHODS OF CLASSES */
	public static ContactDTO valueOf ( final ContactDTO obj ) {
		return new ContactDTO( obj.getId(), obj.getEmail(), obj.getPhoneMobile(), obj.getPhoneHome() );
	}

	public static final Comparator<ContactDTO> comparatorPhoneMobile =
			Comparator.comparing( ( ContactDTO obj ) -> obj.getPhoneMobile().toUpperCase() );
}
