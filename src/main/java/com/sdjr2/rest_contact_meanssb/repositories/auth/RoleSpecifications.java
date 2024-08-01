package com.sdjr2.rest_contact_meanssb.repositories.auth;

import com.sdjr2.rest_contact_meanssb.models.entities.auth.RoleEntity;
import com.sdjr2.rest_contact_meanssb.models.enums.search.OperatorFilterEnum;
import com.sdjr2.rest_contact_meanssb.repositories.filters.BaseSpecifications;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * {@link RoleSpecifications} class.
 * <p>
 * <strong>Repository (DAO)</strong> - Represents a class with the role specifications.
 * <br>
 * It uses the classes : <br> 01. Level Data -> the generic specifications {@link BaseSpecifications}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Repository (DAO)
 * @upgrade 24/08/01
 * @since 24/08/01
 */
public class RoleSpecifications {

	private RoleSpecifications () {
		throw new IllegalStateException( "Utility class" );
	}

	/**
	 * Filters the values of a given attribute of type string that match those provided in the list.
	 *
	 * @param attr   attribute in a database table.
	 * @param values data to filter.
	 * @param op     conditional filter operator.
	 * @return a jpa specification {@link Specification} about attributes of a role {@link RoleEntity}.
	 */
	public static Specification<RoleEntity> hasValuesStr ( String attr, OperatorFilterEnum op, List<String> values ) {
		return ( Objects.nonNull( values ) && values.size() == 1 )
				? BaseSpecifications.filterHas( attr, op, values.get( 0 ) )
				: BaseSpecifications.filterIn( attr, op, values );
	}

	/**
	 * Filters the values of a given attribute of type int that match those provided in the list.
	 *
	 * @param attr   attribute in a database table.
	 * @param values data to filter.
	 * @param op     conditional filter operator.
	 * @return a jpa specification {@link Specification} about attributes of a role {@link RoleEntity}.
	 */
	public static Specification<RoleEntity> hasValuesInt ( String attr, OperatorFilterEnum op, List<Integer> values ) {
		return ( Objects.nonNull( values ) && values.size() == 1 )
				? BaseSpecifications.filterHas( attr, op, values.get( 0 ) )
				: BaseSpecifications.filterIn( attr, op, values );
	}

	/**
	 * Filters the values of a given attribute of type date that match those provided.
	 *
	 * @param attr  attribute in a database table.
	 * @param value data to filter.
	 * @param op    conditional filter operator.
	 * @return a jpa specification {@link Specification} about attributes of a role {@link RoleEntity}.
	 */
	public static Specification<RoleEntity> hasValuesStr ( String attr, OperatorFilterEnum op, LocalDate value ) {
		return BaseSpecifications.filterLocalDate( attr, op, value );
	}
}
