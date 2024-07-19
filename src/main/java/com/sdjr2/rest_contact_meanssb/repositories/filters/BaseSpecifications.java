package com.sdjr2.rest_contact_meanssb.repositories.filters;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * {@link BaseSpecifications} class.
 * <p>
 * <strong>Repository (DAO)</strong> - Represents a class with the generic specifications.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Repository (DAO)
 * @upgrade 24/07/18
 * @since 24/07/18
 */
public class BaseSpecifications {

	private BaseSpecifications () {
		throw new IllegalStateException( "Utility class" );
	}

	/**
	 * Filters the value of a given attribute that match those provided.
	 *
	 * @param attr  attribute in a database table.
	 * @param value value to filter.
	 * @return a jpa specification {@link Specification<T>}.
	 */
	public static <T, V> Specification<T> filterHas ( String attr, V value ) {
		return ( root, cq, cb ) -> ( Objects.nonNull( value ) ) ? cb.equal( root.get( attr ), value ) : cb.and();
	}

	/**
	 * Filters the values of a given attribute that match those provided in the list.
	 *
	 * @param attr attribute in a database table.
	 * @param list list of values to filter.
	 * @return a jpa specification {@link Specification<T>}.
	 */
	public static <T, V> Specification<T> filterIn ( String attr, List<V> list ) {
		return ( root, cq, cb ) -> ( Objects.nonNull( list ) && !list.isEmpty() ) ? root.get( attr ).in( list ) : cb.and();
	}

	/**
	 * Filters the values of a given attribute that match the provided in the date.
	 *
	 * @param attr       attribute in a database table.
	 * @param date       date to filter.
	 * @param isLessThan indicates whether the date to be filtered is before or after the values in the database.
	 * @return a jpa specification {@link Specification<T>}.
	 */
	public static <T> Specification<T> filterLocalDate ( String attr, LocalDate date, boolean isLessThan ) {
		return ( root, cq, cb ) -> {
			if ( Objects.nonNull( date ) ) {
				if ( isLessThan ) return cb.lessThanOrEqualTo( root.get( attr ), date );
				return cb.greaterThanOrEqualTo( root.get( attr ), date );
			}

			return cb.and();
		};
	}
}
