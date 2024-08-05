package com.sdjr2.rest_contact_meanssb.models.dto.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdjr2.rest_contact_meanssb.models.dto.BaseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * {@link FilterDTO} class.
 * <p>
 * <strong>DTO</strong> - Represents the pagination resulting from an advanced pagination, implements to
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
public class PaginationDTO<T extends BaseDTO> implements BaseDTO, Serializable {

	@Serial
	private static final long serialVersionUID = 8875130565759543975L;

	/*
	 * attribute page index
	 */
	@JsonProperty("page_index")
	@Min(0)
	private int pageIndex;

	/*
	 * attribute page size
	 */
	@JsonProperty("page_size")
	@Min(5)
	private int pageSize;

	/*
	 * attribute total pages
	 */
	@JsonProperty("total_pages")
	@Min(0)
	private int totalPages;

	/*
	 * attribute number of elements
	 */
	@JsonProperty("number_of_elements")
	@Min(0)
	private int numberOfElements;

	/*
	 * attribute total elements
	 */
	@JsonProperty("total_elements")
	@Min(0)
	private int totalElements;

	/*
	 * attribute content
	 */
	@Valid
	private List<T> content;

	/*
	 * attribute is first
	 */
	@JsonProperty("first")
	private boolean isFirst;

	/*
	 * attribute is last
	 */
	@JsonProperty("last")
	private boolean isLast;
}
