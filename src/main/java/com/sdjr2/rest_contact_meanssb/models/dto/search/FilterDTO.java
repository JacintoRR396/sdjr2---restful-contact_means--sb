package com.sdjr2.rest_contact_meanssb.models.dto.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdjr2.rest_contact_meanssb.models.dto.BaseDTO;
import com.sdjr2.rest_contact_meanssb.models.enums.search.OperatorFilterEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * {@link FilterDTO} class.
 * <p>
 * <strong>DTO</strong> - Represents a generic filter used by advanced search, implements to
 * {@link BaseDTO}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category DTO
 * @upgrade 24/07/17
 * @since 24/07/17
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FilterDTO implements BaseDTO {

	/*
	 * attribute field
	 */
	@NotNull
	private String field;

	/*
	 * attribute operator filter
	 */
	@JsonProperty("operator_type")
	@NotNull
	@Valid
	public OperatorFilterEnum operatorType;

	/*
	 * attribute values
	 */
	@NotNull
	public List<String> values;
}
