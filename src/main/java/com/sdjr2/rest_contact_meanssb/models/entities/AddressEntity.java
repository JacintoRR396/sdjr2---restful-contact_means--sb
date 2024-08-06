package com.sdjr2.rest_contact_meanssb.models.entities;

import com.sdjr2.rest_contact_meanssb.utils.UConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

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
 * @upgrade 24/07/31
 * @since 23/06/10
 */
@Entity
@Table(name = "dmd_address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressEntity implements BaseEntity, Serializable {

	@Serial
	private static final long serialVersionUID = 5622094071967246451L;

	public static final String TABLE_DMD_NAME = "dmd_address";
	public static final String ATTR_ID = "id";
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
	@Column(name = ATTR_ID, unique = true, nullable = false, updatable = false)
	private Long id;

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
	@Column(name = ATTR_LETTER, nullable = false, length = 3)
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

	/**
	 * method to provide certain functionality before creation
	 */
	@PrePersist
	public void prePersist () {
		if ( Objects.isNull( this.letter ) ) {
			this.letter = UConstants.NOT_APPLY;
		}
	}

	/**
	 * method to update certain functionality before creation
	 */
	@PreUpdate
	public void preUpdate () {
		if ( Objects.isNull( this.letter ) ) {
			this.letter = UConstants.NOT_APPLY;
		}
	}
}
