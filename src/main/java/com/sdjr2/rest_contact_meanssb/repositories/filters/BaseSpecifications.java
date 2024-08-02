package com.sdjr2.rest_contact_meanssb.repositories.filters;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.entities.BaseEntity;
import com.sdjr2.rest_contact_meanssb.models.enums.search.OperatorFilterEnum;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
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
 * @upgrade 24/08/02
 * @since 24/07/18
 */
public abstract class BaseSpecifications<T extends BaseEntity> {

	/**
	 * Filters the values of a given attribute of type string that match those provided in the list.
	 *
	 * @param attr   attribute in a database table.
	 * @param values data to filter.
	 * @param op     conditional filter operator.
	 * @return a jpa specification {@link Specification<T>}.
	 */
	public Specification<T> hasValuesStr ( String attr, OperatorFilterEnum op, List<String> values ) {
		return ( Objects.nonNull( values ) && values.size() == 1 )
				? this.filterHas( attr, op, values.get( 0 ) )
				: this.filterIn( attr, op, values );
	}

	/**
	 * Filters the values of a given attribute of type int that match those provided in the list.
	 *
	 * @param attr   attribute in a database table.
	 * @param values data to filter.
	 * @param op     conditional filter operator.
	 * @return a jpa specification {@link Specification<T>}.
	 */
	public Specification<T> hasValuesInt ( String attr, OperatorFilterEnum op, List<Integer> values ) {
		return ( Objects.nonNull( values ) && values.size() == 1 )
				? this.filterHas( attr, op, values.get( 0 ) )
				: this.filterIn( attr, op, values );
	}

	/**
	 * Filters the values of a given attribute of type boolean that match those provided in the list.
	 *
	 * @param attr   attribute in a database table.
	 * @param values data to filter.
	 * @param op     conditional filter operator.
	 * @return a jpa specification {@link Specification<T>}.
	 */
	public Specification<T> hasValuesBool ( String attr, OperatorFilterEnum op, List<Boolean> values ) {
		return ( Objects.nonNull( values ) && values.size() == 1 )
				? this.filterIs( attr, op, values.get( 0 ) )
				: this.filterIn( attr, op, values );
	}

	/**
	 * Filters the values of a given attribute of type date time that match those provided.
	 *
	 * @param attr   attribute in a database table.
	 * @param values data to filter.
	 * @param op     conditional filter operator.
	 * @return a jpa specification {@link Specification<T>}.
	 */
	public Specification<T> hasValuesLocalDateTime ( String attr, OperatorFilterEnum op, List<LocalDateTime> values ) {
		return ( Objects.nonNull( values ) && values.size() == 1 )
				? this.filterLocalDateTime( attr, op, values.get( 0 ) )
				: this.filterIn( attr, op, values );
	}

	/**
	 * Filters the value of a given attribute that match those provided.
	 *
	 * @param attr  attribute in a database table.
	 * @param op    conditional filter operator.
	 * @param value value to filter.
	 * @return a jpa specification {@link Specification<T>}.
	 */
	private <V> Specification<T> filterHas ( String attr, OperatorFilterEnum op, V value ) {
		return ( root, query, builder ) -> {
			if ( Objects.nonNull( value ) ) {
				return switch ( op ) {
					case EQ -> builder.equal( root.get( attr ), value );
					case NEQ -> builder.notEqual( root.get( attr ), value );
					case GT -> builder.greaterThan( root.get( attr ), value.toString() );
					case GTE -> builder.greaterThanOrEqualTo( root.get( attr ), value.toString() );
					case LT -> builder.lessThan( root.get( attr ), value.toString() );
					case LTE -> builder.lessThanOrEqualTo( root.get( attr ), value.toString() );
					case SW -> builder.like( root.get( attr ), value + "%" );
					case EW -> builder.like( root.get( attr ), "%" + value );
					case CT -> builder.like( root.get( attr ), "%" + value + "%" );
					// case NCT -> builder.notLike( root.get( attr ), value );
					case IS_NULL -> builder.isNull( root.get( attr ) );
					case IS_NOT_NULL -> builder.isNotNull( root.get( attr ) );
					default -> throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
				};
			}

			return builder.and();
		};
	}

	/**
	 * Filters the values of a given attribute that match those provided in the list.
	 *
	 * @param attr attribute in a database table.
	 * @param op   conditional filter operator.
	 * @param list list of values to filter.
	 * @return a jpa specification {@link Specification<T>}.
	 */
	private <V> Specification<T> filterIn ( String attr, OperatorFilterEnum op, List<V> list ) {
		return ( root, query, builder ) -> {
			if ( Objects.nonNull( list ) && !list.isEmpty() ) {
				return switch ( op ) {
					case IN -> root.get( attr ).in( list );
					case NIN -> root.get( attr ).in( list ).not();
					default -> throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
				};
			}

			return builder.and();
		};
	}

	/**
	 * Filters the value of a given attribute that match those provided.
	 *
	 * @param attr  attribute in a database table.
	 * @param op    conditional filter operator.
	 * @param value value to filter.
	 * @return a jpa specification {@link Specification<T>}.
	 */
	private <V> Specification<T> filterIs ( String attr, OperatorFilterEnum op, V value ) {
		return ( root, query, builder ) -> {
			if ( Objects.nonNull( value ) ) {
				return switch ( op ) {
					case EQ -> builder.equal( root.get( attr ), value );
					case NEQ -> builder.notEqual( root.get( attr ), value );
					default -> throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
				};
			}

			return builder.and();
		};
	}

	/**
	 * Filters the values of a given attribute that match the provided in the date.
	 *
	 * @param attr  attribute in a database table.
	 * @param op    conditional filter operator.
	 * @param value date to filter.
	 * @return a jpa specification {@link Specification<T>}.
	 */
	private Specification<T> filterLocalDateTime ( String attr, OperatorFilterEnum op, LocalDateTime value ) {
		return ( root, query, builder ) -> {
			if ( Objects.nonNull( value ) ) {
				return switch ( op ) {
					case EQ -> builder.equal( root.get( attr ), value );
					case NEQ -> builder.notEqual( root.get( attr ), value );
					case GT -> builder.greaterThan( root.get( attr ), value );
					case GTE -> builder.greaterThanOrEqualTo( root.get( attr ), value );
					case LT -> builder.lessThan( root.get( attr ), value );
					case LTE -> builder.lessThanOrEqualTo( root.get( attr ), value );
					case IS_NULL -> builder.isNull( root.get( attr ) );
					case IS_NOT_NULL -> builder.isNotNull( root.get( attr ) );
					default -> throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
				};
			}

			return builder.and();
		};
	}
}
