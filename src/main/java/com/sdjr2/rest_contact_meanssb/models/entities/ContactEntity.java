package com.sdjr2.rest_contact_meanssb.models.entities;

import com.sdjr2.sb.library_commons.models.entities.AuditableEntity;
import com.sdjr2.sb.library_commons.models.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * {@link ContactEntity} class.
 * <p>
 * <strong>Entity (ORM)</strong> - Represents a Contact in the database, implements to
 * {@link BaseEntity}.
 * <p>
 * It uses the classes : <br> 01. Level Data -> the entity {@link AuditableEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Entity (ORM)
 * @upgrade 24/08/12
 * @since 24/08/12
 */
@Entity
@Table(name = "dmd_contact")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactEntity implements BaseEntity, Serializable {

	@Serial
	private static final long serialVersionUID = -2022483706143543550L;

	public static final String TABLE_DMD_NAME = "dmd_contact";
	public static final String ATTR_ID = "id";
	public static final String ATTR_EMAIL = "email";
	public static final String ATTR_PHONE_MOBILE = "phone_mobile";
	public static final String ATTR_PHONE_HOME = "phone_home";

	/**
	 * contact id identifier
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ATTR_ID, unique = true, nullable = false, updatable = false)
	private Long id;

	/**
	 * email attribute
	 */
	@Column(name = ATTR_EMAIL, nullable = false, unique = true, length = 40)
	private String email;

	/**
	 * phone mobile attribute
	 */
	@Column(name = ATTR_PHONE_MOBILE, nullable = false, length = 12)
	private String phoneMobile;

	/**
	 * phone home attribute
	 */
	@Column(name = ATTR_PHONE_HOME, length = 12)
	private String phoneHome;

	/**
	 * entity with attributes about audit
	 */
	@Embedded
	private AuditableEntity auditableEntity = new AuditableEntity();
}
