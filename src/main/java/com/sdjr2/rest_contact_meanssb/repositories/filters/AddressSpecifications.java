package com.sdjr2.rest_contact_meanssb.repositories.filters;

import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
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
 * @upgrade 24/07/21
 * @since 24/07/18
 */
public class AddressSpecifications {

	private AddressSpecifications () {
		throw new IllegalStateException( "Utility class" );
	}

	/**
	 * Filters the values of a certain attribute of type int that match those provided in the list.
	 *
	 * @param values data to filter.
	 * @return a jpa specification {@link Specification} about attributes of an address {@link AddressEntity}.
	 */
	public static Specification<AddressEntity> hasValuesInt ( String attr, List<Integer> values ) {
		return BaseSpecifications.filterIn( attr, values );
	}

	/**
	 * Filters the values of a certain attribute of type string that match those provided in the list.
	 *
	 * @param values data to filter.
	 * @return a jpa specification {@link Specification} about attributes of an address {@link AddressEntity}.
	 */
	public static Specification<AddressEntity> hasValuesStr ( String attr, List<String> values ) {
		return BaseSpecifications.filterIn( attr, values );
	}
}
