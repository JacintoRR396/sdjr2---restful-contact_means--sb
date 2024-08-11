package com.sdjr2.rest_contact_meanssb.models.entities.auth;

import com.sdjr2.sb.library_commons.models.entities.AuditableEntity;
import com.sdjr2.sb.library_commons.models.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * {@link UserEntity} class.
 * <p>
 * <strong>Entity (ORM)</strong> - Represents a User in the database, implements to
 * {@link BaseEntity}.
 * <p>
 * It uses the classes : <br> 01. Level Data -> the entity {@link AuditableEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Entity (ORM)
 * @upgrade 24/08/11
 * @since 24/07/31
 */
@Entity
@Table(name = "dmd_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity implements BaseEntity, Serializable {

	@Serial
	private static final long serialVersionUID = 131649805290675146L;

	public static final String TABLE_DMD_NAME = "dmd_user";
	public static final String ATTR_ID = "id";
	public static final String ATTR_USERNAME = "username";
	public static final String ATTR_PWD = "pwd";
	public static final String ATTR_NICKNAME = "nickname";
	public static final String ATTR_EMAIL = "email";
	public static final String ATTR_IS_ENABLED = "enabled";
	public static final String ATTR_LAST_ACCESS = "last_access";
	public static final String ATTR_ROLES = "roles";

	public static final String TABLE_DMR_WROLE_NAME = "dmr_user_role";

	/**
	 * user id identifier
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ATTR_ID, unique = true, nullable = false, updatable = false)
	private Long id;

	/**
	 * username attribute
	 */
	@Column(name = ATTR_USERNAME, nullable = false, unique = true, length = 40)
	private String username;

	/**
	 * pwd attribute
	 */
	@Column(name = ATTR_PWD, nullable = false, length = 60)
	private String pwd;

	/**
	 * nickname attribute
	 */
	@Column(name = ATTR_NICKNAME, length = 30)
	private String nickname;

	/**
	 * email attribute
	 */
	@Column(name = ATTR_EMAIL, nullable = false, length = 40)
	private String email;

	/**
	 * is active attribute
	 */
	@Column(name = ATTR_IS_ENABLED, nullable = false)
	private Boolean enabled;

	/**
	 * created at attribute
	 */
	@Column(name = ATTR_LAST_ACCESS, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime lastAccess;

	/**
	 * roles attribute
	 */
	@ManyToMany
	@JoinTable(
			name = "dmr_user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"),
			uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "role_id" }) }
	)
	private List<RoleEntity> roles;

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
		this.enabled = true;
		this.lastAccess = LocalDateTime.now();
	}
}
