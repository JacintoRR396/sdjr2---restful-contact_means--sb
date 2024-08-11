package com.sdjr2.rest_contact_meanssb.models.enums.auth;

import com.sdjr2.rest_contact_meanssb.models.dto.auth.RoleDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.auth.RoleEntity;
import com.sdjr2.sb.library_commons.exceptions.AppExceptionCodeEnum;
import com.sdjr2.sb.library_commons.exceptions.CustomException;
import com.sdjr2.sb.library_commons.models.dto.search.FilterDTO;
import com.sdjr2.sb.library_commons.models.enums.OperatorFilterEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * {@link RoleFilterFieldEnum} enum.
 * <p>
 * <strong>Enum (Model)</strong> - Represents an enum regarding the allowed roles attributes used by the filters in
 * advanced search.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Enum (Model)
 * @upgrade 24/08/11
 * @since 24/08/01
 */
@Getter
public enum RoleFilterFieldEnum {
	ID( "ID", RoleDTO.ATTR_ID, RoleEntity.ATTR_ID ),
	NAME( "NAME", RoleDTO.ATTR_NAME, RoleEntity.ATTR_NAME );

	private final String value;
	private final String fieldFront;
	private final String fieldMySQL;

	RoleFilterFieldEnum ( String value, String fieldFront, String fieldMySQL ) {
		this.value = value;
		this.fieldFront = fieldFront;
		this.fieldMySQL = fieldMySQL;
	}

	@Override
	public String toString () {
		return this.value;
	}

	public static RoleFilterFieldEnum fromValue ( String value ) {
		for ( RoleFilterFieldEnum type : RoleFilterFieldEnum.values() ) {
			if ( type.getValue().equals( value ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static RoleFilterFieldEnum fromFieldFront ( String fieldFront ) {
		for ( RoleFilterFieldEnum type : RoleFilterFieldEnum.values() ) {
			if ( type.getFieldFront().equals( fieldFront ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static RoleFilterFieldEnum fromFieldMySQL ( String fieldMySQL ) {
		for ( RoleFilterFieldEnum type : RoleFilterFieldEnum.values() ) {
			if ( type.getFieldMySQL().equals( fieldMySQL ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static RoleFiltersRequest getFiltersReqFromSearchDTO ( List<FilterDTO> filtersDTO ) {
		RoleFiltersRequest.RoleFiltersRequestBuilder builder = RoleFiltersRequest.builder();
		for ( FilterDTO filterDTO : filtersDTO ) {
			switch ( RoleFilterFieldEnum.fromValue( filterDTO.getField() ) ) {
				case ID -> {
					builder.ids( filterDTO.getValues().stream().map( Integer::valueOf ).toList() );
					builder.opIds( filterDTO.getOperatorType() );
				}
				case NAME -> {
					builder.names( filterDTO.getValues() );
					builder.opNames( filterDTO.getOperatorType() );
				}
				default -> throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
			}
		}

		return builder.build();
	}

	@Data
	@Builder
	public static class RoleFiltersRequest {
		private List<Integer> ids;
		private OperatorFilterEnum opIds;
		private List<String> names;
		private OperatorFilterEnum opNames;
	}
}
