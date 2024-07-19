package com.sdjr2.rest_contact_meanssb.models.enums.search;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import lombok.Getter;

/**
 * {@link OperatorFilterEnum} enum.
 * <p>
 * <strong>Enum (Model)</strong> - Represents an enum regarding the types of relational operators used by the filters
 * in advanced search.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Enum (Model)
 * @upgrade 24/07/18
 * @since 24/07/17
 */
@Getter
public enum OperatorFilterEnum {
	EQ( "eq", "=", "eq" ),
	NEQ( "neq", "<>", "ne" ),
	GT( "gt", ">", "gt" ),
	GTE( "gte", ">=", "gte" ),
	LT( "lt", "<", "lt" ),
	LTE( "lte", "<=", "lte" ),
	SW( "sw", "LIKE", "like" ),
	EW( "ew", "LIKE", "like" ),
	CT( "ct", "LIKE", "like" ),
	NCT( "nct", "NOT LIKE", "" ),
	IS_NULL( "null", "IS NULL", "" ),
	NOT_NULL( "not_null", "IS NOT NULL", "" );

	private final String value;
	private final String operatorMySQL;
	private final String operatorGraphQL;

	OperatorFilterEnum ( String value, String operatorMySQL, String operatorGraphQL ) {
		this.value = value;
		this.operatorMySQL = operatorMySQL;
		this.operatorGraphQL = operatorGraphQL;
	}

	@Override
	public String toString () {
		return this.value;
	}

	public static OperatorFilterEnum fromValue ( String value ) {
		for ( OperatorFilterEnum type : OperatorFilterEnum.values() ) {
			if ( type.getValue().equals( value ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static OperatorFilterEnum fromOperatorMySQL ( String operator ) {
		for ( OperatorFilterEnum type : OperatorFilterEnum.values() ) {
			if ( type.getOperatorMySQL().equals( operator ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static OperatorFilterEnum fromOperatorGraphQL ( String operator ) {
		for ( OperatorFilterEnum type : OperatorFilterEnum.values() ) {
			if ( type.getOperatorGraphQL().equals( operator ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}
}
