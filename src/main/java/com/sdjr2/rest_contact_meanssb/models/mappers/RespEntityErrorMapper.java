package com.sdjr2.rest_contact_meanssb.models.mappers;

import com.sdjr2.sb.library_commons.exceptions.CustomException;
import com.sdjr2.sb.library_commons.models.dto.errors.RespEntityErrorDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Arrays;
import java.util.Objects;

/**
 * {@link RespEntityErrorMapper} class.
 * <p>
 * <strong>Mapper</strong> - Represents a converter about ResponseEntity Error DTO and CustomException.
 * <p>
 * It uses the classes : <br> 01. Level Access -> the dto {@link RespEntityErrorDTO} <br> 02. Level Model -> the entity
 * {@link CustomException}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Mapper
 * @upgrade 24/06/18
 * @since 23/06/14
 */
@Mapper(componentModel = "spring", imports = { Arrays.class })
public abstract class RespEntityErrorMapper {

	/**
	 * Map custom exception to response entity error dto.
	 *
	 * @param ex        custom exception
	 * @param timestamp creation date
	 * @return RespEntityErrorDTO {@link RespEntityErrorDTO}
	 */
	@Mapping(source = "ex.id", target = "id")
	@Mapping(source = "timestamp", target = "timestamp")
	@Mapping(expression = "java( ex.getAppExCode().getHttpStatusCode().value() )", target = "httpStatusCode")
	@Mapping(expression = "java( ex.getAppExCode().getAppStatusCode() )", target = "errorCode")
	@Mapping(expression = "java( ex.getAppExCode().getMessage() )", target = "errorMessage")
	@Mapping(target = "validationErrors", ignore = true)
	@Mapping(target = "exMessage", ignore = true)
	@Mapping(target = "exTrackTrace", ignore = true)
	public abstract RespEntityErrorDTO toDTO ( CustomException ex, String timestamp );

	/**
	 * Map custom exception to response entity error dto with additional logic.
	 *
	 * @param ex        custom exception
	 * @param timestamp creation date
	 * @param errorDTO  resp entity error DTO
	 * @return RespEntityErrorDTO {@link RespEntityErrorDTO}
	 */
	@AfterMapping
	protected RespEntityErrorDTO afterMappingTDTO ( CustomException ex, String timestamp,
																									@MappingTarget RespEntityErrorDTO errorDTO ) {
		if ( Objects.nonNull( ex.getValidationErrors() ) ) {
			errorDTO.setValidationErrors( ex.getValidationErrors() );
		}
		if ( Objects.nonNull( ex.getOriginalException() ) ) {
			errorDTO.setExMessage( ex.getOriginalException().getMessage() );
			errorDTO.setExTrackTrace( Arrays.toString( ex.getOriginalException().getStackTrace() ) );
		}

		return errorDTO;
	}
}
