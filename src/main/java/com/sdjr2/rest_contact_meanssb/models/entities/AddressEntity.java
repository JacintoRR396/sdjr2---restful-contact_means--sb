package com.sdjr2.rest_contact_meanssb.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
 * @upgrade 24/06/23
 * @since 23/06/10
 */
@Entity
@Table(name = "dmd_address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressEntity implements BaseEntity, Serializable {

	@Serial
	private static final long serialVersionUID = -8456791947043775284L;
	
	/**
	 * address id identifier
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id", unique = true, nullable = false)
	private Integer id;

	/**
	 * street attribute
	 */
	@Column(name = "street_str", nullable = false, length = 80)
	private String street;

	/**
	 * number of the street attribute
	 */
	@Column(name = "number_str", nullable = false, length = 5)
	private String number;

	/**
	 * letter of the number attribute
	 */
	@Column(name = "letter_str", length = 3)
	private String letter;

	/**
	 * town attribute
	 */
	@Column(name = "town_str", length = 60)
	private String town;

	/**
	 * city attribute
	 */
	@Column(name = "city_str", length = 30)
	private String city;

	/**
	 * country attribute
	 */
	@Column(name = "country_str", length = 20)
	private String country;

	/**
	 * postal code attribute
	 */
	@Column(name = "postal_code_str", nullable = false, length = 5)
	private String postalCode;

	/**
	 * longitude attribute
	 */
	@Column(name = "longitude_str")
	private String longitude;

	/**
	 * latitude attribute
	 */
	@Column(name = "latitude_str")
	private String latitude;

	/**
	 * additional info attribute
	 */
	@Column(name = "additional_info_str", length = 2500)
	private String additionalInfo;

	/**
	 * entity with attributes about audit
	 */
	@Embedded
	private AuditableEntity auditableEntity = new AuditableEntity();
}
