package com.sdjr2.rest_contact_meanssb.models.enums.auth;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.dto.auth.UserDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.auth.UserEntity;
import lombok.Getter;

/**
 * {@link UserSortFieldEnum} enum.
 * <p>
 * <strong>Enum (Model)</strong> - Represents an enum regarding the allowed users attributes used by the sorts in
 * advanced search.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Enum (Model)
 * @upgrade 24/08/01
 * @since 24/08/01
 */
@Getter
public enum UserSortFieldEnum {
	USERNAME( "USERNAME", UserDTO.ATTR_USERNAME, UserEntity.ATTR_USERNAME ),
	NICKNAME( "NICKNAME", UserDTO.ATTR_NICKNAME, UserEntity.ATTR_NICKNAME ),
	EMAIL( "EMAIL", UserDTO.ATTR_EMAIL, UserEntity.ATTR_EMAIL ),
	IS_ENABLED( "IS_ENABLED", UserDTO.ATTR_IS_ENABLED, UserEntity.ATTR_IS_ENABLED );

	private final String value;
	private final String fieldFront;
	private final String fieldMySQL;

	UserSortFieldEnum ( String value, String fieldFront, String fieldMySQL ) {
		this.value = value;
		this.fieldFront = fieldFront;
		this.fieldMySQL = fieldMySQL;
	}

	@Override
	public String toString () {
		return this.value;
	}

	public static UserSortFieldEnum fromValue ( String value ) {
		for ( UserSortFieldEnum type : UserSortFieldEnum.values() ) {
			if ( type.getValue().equals( value ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static UserSortFieldEnum fromFieldFront ( String fieldFront ) {
		for ( UserSortFieldEnum type : UserSortFieldEnum.values() ) {
			if ( type.getFieldFront().equals( fieldFront ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static UserSortFieldEnum fromFieldMySQL ( String fieldMySQL ) {
		for ( UserSortFieldEnum type : UserSortFieldEnum.values() ) {
			if ( type.getFieldMySQL().equals( fieldMySQL ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}
}
