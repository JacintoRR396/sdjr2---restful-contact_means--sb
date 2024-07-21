package com.sdjr2.rest_contact_meanssb.models.dto.search;

import com.sdjr2.rest_contact_meanssb.models.dto.BaseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * {@link FilterDTO} class.
 * <p>
 * <strong>DTO</strong> - Represents the advanced search, implements to {@link BaseDTO}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category DTO
 * @upgrade 24/07/18
 * @since 24/07/17
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchBodyDTO implements BaseDTO {

	/*
	 * attribute page index
	 */
	@Min(0)
	private int offset;

	/*
	 * attribute page size
	 */
	@Min(3)
	private int limit;

	/*
	 * attribute sorts
	 */
	@Valid
	private List<OrderDTO> sorts;

	/*
	 * attribute filters
	 */
	@Valid
	private List<FilterDTO> filters;
}
