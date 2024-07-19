package com.sdjr2.rest_contact_meanssb.repositories.filters;

import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.enums.search.AddressFilterFieldEnum;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * {@link AddressSpecifications} class.
 * <p>
 * <strong>Repository (DAO)</strong> - Represents a class with the address specifications.
 * <br>
 * It uses the classes : <br> 01. Level Data -> the generic specifications {@link BaseSpecifications}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Repository (DAO)
 * @upgrade 24/07/18
 * @since 24/07/18
 */
public class AddressSpecifications {

	private AddressSpecifications () {
		throw new IllegalStateException( "Utility class" );
	}

	/**
	 * filters the values the attribute ccyPair that match those provided in the list.
	 *
	 * @param towns values with the town to filter.
	 * @return a jpa specification {@link Specification} about addresses {@link AddressEntity}.
	 */
	public static Specification<AddressEntity> hasTowns ( List<String> towns ) {
		return BaseSpecifications.filterIn( AddressFilterFieldEnum.TOWN.getFieldMySQL(), towns );
	}
}
