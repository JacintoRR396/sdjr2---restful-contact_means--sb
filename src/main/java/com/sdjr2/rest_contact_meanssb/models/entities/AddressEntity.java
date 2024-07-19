package com.sdjr2.rest_contact_meanssb.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.io.Serial;
import java.io.Serializable;

/**
 * {@link AddressEntity} class.
 * <p>
 * <strong>Entity (ORM)</strong> - Represents an Address in the database, implements to
 * {@link BaseEntity}.
 * <p>
 * It uses the classes : <br> 01. Level Data -> the entity {@link AuditableEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Entity (ORM)
 * @upgrade 24/07/18
 * @since 23/06/10
 */
@Entity
@Table(name = "dmd_address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Immutable
public class AddressEntity implements BaseEntity, Serializable {

	@Serial
	private static final long serialVersionUID = -8456791947043775284L;

	public static final String TABLE_NAME = "dmd_address";
	public static final String ATTR_ID = "address_id";
	public static final String ATTR_STREET = "street";
	public static final String ATTR_NUMBER = "number";
	public static final String ATTR_LETTER = "letter";
	public static final String ATTR_TOWN = "town";
	public static final String ATTR_CITY = "city";
	public static final String ATTR_COUNTRY = "country";
	public static final String ATTR_POSTAL_CODE = "postal_code";
	public static final String ATTR_LONGITUDE = "longitude";
	public static final String ATTR_LATITUDE = "latitude";
	public static final String ATTR_ADDITIONAL_INFO = "additional_info";

	/**
	 * address id identifier
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ATTR_ID, unique = true, nullable = false)
	private Integer id;

	/**
	 * street attribute
	 */
	@Column(name = ATTR_STREET, nullable = false, length = 80)
	private String street;

	/**
	 * number of the street attribute
	 */
	@Column(name = ATTR_NUMBER, nullable = false, length = 5)
	private String number;

	/**
	 * letter of the number attribute
	 */
	@Column(name = ATTR_LETTER, length = 3)
	private String letter;

	/**
	 * town attribute
	 */
	@Column(name = ATTR_TOWN, length = 60)
	private String town;

	/**
	 * city attribute
	 */
	@Column(name = ATTR_CITY, length = 30)
	private String city;

	/**
	 * country attribute
	 */
	@Column(name = ATTR_COUNTRY, length = 20)
	private String country;

	/**
	 * postal code attribute
	 */
	@Column(name = ATTR_POSTAL_CODE, nullable = false, length = 5)
	private String postalCode;

	/**
	 * longitude attribute
	 */
	@Column(name = ATTR_LONGITUDE)
	private String longitude;

	/**
	 * latitude attribute
	 */
	@Column(name = ATTR_LATITUDE)
	private String latitude;

	/**
	 * additional info attribute
	 */
	@Column(name = ATTR_ADDITIONAL_INFO, length = 2500)
	private String additionalInfo;

	/**
	 * entity with attributes about audit
	 */
	@Embedded
	private AuditableEntity auditableEntity = new AuditableEntity();
}
