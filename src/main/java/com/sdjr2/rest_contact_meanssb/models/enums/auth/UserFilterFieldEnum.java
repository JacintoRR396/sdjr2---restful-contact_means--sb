package com.sdjr2.rest_contact_meanssb.models.enums.auth;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.dto.auth.UserDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.FilterDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.auth.UserEntity;
import com.sdjr2.rest_contact_meanssb.models.enums.search.OperatorFilterEnum;
import com.sdjr2.rest_contact_meanssb.utils.UConstants;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * {@link UserFilterFieldEnum} enum.
 * <p>
 * <strong>Enum (Model)</strong> - Represents an enum regarding the allowed users attributes used by the filters in
 * advanced search.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Enum (Model)
 * @upgrade 24/08/02
 * @since 24/08/01
 */
@Getter
public enum UserFilterFieldEnum {
	ID( "ID", UserDTO.ATTR_ID, UserDTO.ATTR_ID ),
	USERNAME( "USERNAME", UserDTO.ATTR_USERNAME, UserEntity.ATTR_USERNAME ),
	NICKNAME( "NICKNAME", UserDTO.ATTR_NICKNAME, UserEntity.ATTR_NICKNAME ),
	EMAIL( "EMAIL", UserDTO.ATTR_EMAIL, UserEntity.ATTR_EMAIL ),
	IS_ENABLED( "IS_ENABLED", UserDTO.ATTR_IS_ENABLED, UserEntity.ATTR_IS_ENABLED ),
	LAST_ACCESS( "LAST_ACCESS", UserDTO.ATTR_LAST_ACCESS, UserEntity.ATTR_LAST_ACCESS );

	private final String value;
	private final String fieldFront;
	private final String fieldMySQL;

	UserFilterFieldEnum ( String value, String fieldFront, String fieldMySQL ) {
		this.value = value;
		this.fieldFront = fieldFront;
		this.fieldMySQL = fieldMySQL;
	}

	@Override
	public String toString () {
		return this.value;
	}

	public static UserFilterFieldEnum fromValue ( String value ) {
		for ( UserFilterFieldEnum type : UserFilterFieldEnum.values() ) {
			if ( type.getValue().equals( value ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static UserFilterFieldEnum fromFieldFront ( String fieldFront ) {
		for ( UserFilterFieldEnum type : UserFilterFieldEnum.values() ) {
			if ( type.getFieldFront().equals( fieldFront ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static UserFilterFieldEnum fromFieldMySQL ( String fieldMySQL ) {
		for ( UserFilterFieldEnum type : UserFilterFieldEnum.values() ) {
			if ( type.getFieldMySQL().equals( fieldMySQL ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static UserFiltersRequest getFiltersReqFromSearchDTO ( List<FilterDTO> filtersDTO ) {
		UserFiltersRequest.UserFiltersRequestBuilder builder = UserFiltersRequest.builder();
		for ( FilterDTO filterDTO : filtersDTO ) {
			switch ( UserFilterFieldEnum.fromValue( filterDTO.getField() ) ) {
				case ID -> {
					builder.ids( filterDTO.getValues().stream().map( Integer::valueOf ).toList() );
					builder.opIds( filterDTO.getOperatorType() );
				}
				case USERNAME -> {
					builder.usernames( filterDTO.getValues() );
					builder.opUsernames( filterDTO.getOperatorType() );
				}
				case NICKNAME -> {
					builder.nicknames( filterDTO.getValues() );
					builder.opNicknames( filterDTO.getOperatorType() );
				}
				case EMAIL -> {
					builder.emails( filterDTO.getValues() );
					builder.opEmails( filterDTO.getOperatorType() );
				}
				case IS_ENABLED -> {
					builder.isEnableds( filterDTO.getValues().stream().map( Boolean::valueOf ).toList() );
					builder.opIsEnableds( filterDTO.getOperatorType() );
				}
				case LAST_ACCESS -> {
					builder.lastAccesses( filterDTO.getValues().stream()
							.map( value -> LocalDateTime.parse( value, DateTimeFormatter.ofPattern( UConstants.S_FORMAT_DATETIME_FRONT ) ) )
							.toList() );
					builder.opLastAccesses( filterDTO.getOperatorType() );
				}
				default -> throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
			}
		}

		return builder.build();
	}

	@Data
	@Builder
	public static class UserFiltersRequest {
		private List<Integer> ids;
		private OperatorFilterEnum opIds;
		private List<String> usernames;
		private OperatorFilterEnum opUsernames;
		private List<String> nicknames;
		private OperatorFilterEnum opNicknames;
		private List<String> emails;
		private OperatorFilterEnum opEmails;
		private List<Boolean> isEnableds;
		private OperatorFilterEnum opIsEnableds;
		private List<LocalDateTime> lastAccesses;
		private OperatorFilterEnum opLastAccesses;
	}
}
