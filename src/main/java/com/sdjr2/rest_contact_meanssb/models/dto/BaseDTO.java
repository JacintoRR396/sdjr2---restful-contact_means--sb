package com.sdjr2.rest_contact_meanssb.models.dto;

import com.sdjr2.rest_contact_meanssb.utils.UJsonAdapter;

/**
 * {@link BaseDTO} class.
 * <p>
 * <strong>DTO</strong> - Interface with the common dto.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category DTO
 * @upgrade 24/07/15
 * @since 23/06/20
 */
public interface BaseDTO {

	static BaseDTO toJsonObj ( final String jsonData ) {
		return UJsonAdapter.readValue( jsonData, BaseDTO.class );
	}

	static String toJsonStr ( final BaseDTO objWebResp ) {
		return UJsonAdapter.writeValueAsString( objWebResp );
	}
}
