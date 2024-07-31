package com.sdjr2.rest_contact_meanssb.models.entities.auth;

import com.sdjr2.rest_contact_meanssb.models.entities.AuditableEntity;
import com.sdjr2.rest_contact_meanssb.models.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * {@link RoleEntity} class.
 * <p>
 * <strong>Entity (ORM)</strong> - Represents a Role in the database, implements to
 * {@link BaseEntity}.
 * <p>
 * It uses the classes : <br> 01. Level Data -> the entity {@link AuditableEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Entity (ORM)
 * @upgrade 24/07/31
 * @since 24/07/31
 */
@Entity
@Table(name = "dmp_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleEntity implements BaseEntity, Serializable {

	@Serial
	private static final long serialVersionUID = -8456791947043775284L;

	public static final String TABLE_DMP_NAME = "dmp_role";
	public static final String ATTR_ID = "id";
	public static final String ATTR_NAME = "name";
	public static final String ATTR_DESCRIPTION = "description";

	/**
	 * role id identifier
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ATTR_ID, unique = true, nullable = false, updatable = false)
	private Long id;

	/**
	 * name attribute
	 */
	@Column(name = ATTR_NAME, nullable = false, unique = true, length = 20)
	private String name;

	/**
	 * description attribute
	 */
	@Column(name = ATTR_DESCRIPTION, nullable = false, length = 300)
	private String description;

	/**
	 * entity with attributes about audit
	 */
	@Embedded
	private AuditableEntity auditableEntity = new AuditableEntity();
}
