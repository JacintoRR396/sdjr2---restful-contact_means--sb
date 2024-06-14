package com.sdjr2.rest_contact_meanssb.mappers;

import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.errors.RespEntityErrorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;

/**
 * {@link RespEntityErrorMapper} class.
 * <p>
 * <strong>Mapper</strong> - Represents a converter about ResponseEntity Error DTO and CustomException.
 * <p>
 * It uses the classes : 01. Level Access -> the dto {@link RespEntityErrorDTO} 02. Level Model -> the entity
 * {@link CustomException}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Mapper
 * @upgrade 24/06/14
 * @since 23/06/14
 */
@Mapper(componentModel = "spring", imports = {Arrays.class})
public abstract class RespEntityErrorMapper {

  /**
   * Map custom exception to response entity error dto.
   *
   * @param ex custom exception
   * @return RespEntityErrorDTO {@link RespEntityErrorDTO}
   */
  @Mapping(source = "ex.id", target = "id")
  @Mapping(source = "ex.date", target = "date")
  @Mapping(expression = "java( ex.getAppExCode().getHttpStatusCode().value() )", target = "httpStatusCode")
  @Mapping(expression = "java( ex.getAppExCode().getAppStatusCode() )", target = "errorCode")
  @Mapping(expression = "java( ex.getAppExCode().getMessage() )", target = "errorMessage")
  @Mapping(expression = "java( ex.getOriginalException().getMessage() )", target = "exMessage")
  @Mapping(expression = "java( Arrays.toString(ex.getOriginalException().getStackTrace()) )", target = "exTrackTrace")
  public abstract RespEntityErrorDTO toDTO(CustomException ex);
}
