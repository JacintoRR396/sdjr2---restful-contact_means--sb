package com.sdjr2.rest_contact_meanssb.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {@link AddressEntity} class.
 * <p>
 * <strong>Entity</strong> - Represents an Address in the database.
 * <p>
 * It uses the classes : 01. Level Data -> the entity {@link AuditableEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Entity (ORM)
 * @upgrade 24/06/11
 * @since 23/06/10
 */
@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressEntity {

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
	@Column(name = "street", nullable = false, length = 120)
	private String street;

	/**
	 * number of the street attribute
	 */
	@Column(name = "number", nullable = false, length = 5)
	private Integer number;

	/**
	 * letter of the number attribute
	 */
	@Column(name = "letter", length = 3)
	private String letter;

	/**
	 * town attribute
	 */
	@Column(name = "town", length = 80)
	private String town;

	/**
	 * city attribute
	 */
	@Column(name = "city", length = 60)
	private String city;

	/**
	 * country attribute
	 */
	@Column(name = "country", length = 40)
	private String country;

	/**
	 * postal code attribute
	 */
	@Column(name = "postal_code", nullable = false, length = 5)
	private Integer postalCode;

	/**
	 * latitude attribute
	 */
	@Column(name = "latitude")
	private Double latitude;

	/**
	 * longitude attribute
	 */
	@Column(name = "longitude")
	private Double longitude;

	/**
	 * additional info attribute
	 */
	@Column(name = "additional_info", length = 10)
	private String additionalInfo;
}
