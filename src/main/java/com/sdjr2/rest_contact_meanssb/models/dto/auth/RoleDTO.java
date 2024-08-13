package com.sdjr2.rest_contact_meanssb.models.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdjr2.sb.library_commons.models.dto.BaseDTO;
import com.sdjr2.sb.library_commons.utils.UConstants;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * {@link RoleDTO} class.
 * <p>
 * <strong>DTO</strong> - Represents a Role in the Request / Response, implements to
 * {@link BaseDTO}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category DTO
 * @upgrade 24/08/12
 * @since 24/08/01
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO implements BaseDTO, Comparable<RoleDTO>, Serializable {

	@Serial
	private static final long serialVersionUID = 3474589087053457452L;

	public static final String ATTR_ID = "id";
	public static final String ATTR_NAME = "name";
	public static final String ATTR_DESCRIPTION = "description";

	/* VARIABLES */
	@PositiveOrZero
	@Digits(integer = 8, fraction = 0)
	private Long id;

	@NotNull
	@Size(min = 6, max = 20)
	@Pattern(regexp = UConstants.REGEX_ROLE)
	private String name;

	@NotNull
	@Size(min = 10, max = 300)
	@Pattern(regexp = UConstants.REGEX_ADDITIONAL_INFO)
	private String description;

	/* METHODS OF INSTANCE */
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.id.hashCode();
		result = prime * result + this.name.hashCode();
		return result;
	}

	@Override
	public boolean equals ( final Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( !( obj instanceof RoleDTO other ) ) {
			return false;
		}
		return this.id.equals( other.getId() )
				&& this.name.equals( other.getName() );
	}

	@Override
	public String toString () {
		final StringBuilder res = new StringBuilder(
				"The DTO '" + this.getClass().getSimpleName() + "' contains the attributes: \n" );
		res.append( " - Id » " ).append( this.id ).append( ".\n" );
		res.append( " - Name » " ).append( this.name ).append( ".\n" );
		res.append( " - Description » " ).append( this.description ).append( ".\n" );

		return res.toString();
	}

	@Override
	public int compareTo ( final RoleDTO obj ) {
		// Name
		return this.getName().compareTo( obj.getName() );
	}

	/* METHODS OF CLASSES */
	public static RoleDTO valueOf ( final RoleDTO obj ) {
		return new RoleDTO( obj.getId(), obj.getName(), obj.getDescription() );
	}
}
