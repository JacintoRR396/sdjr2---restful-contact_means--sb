package com.sdjr2.rest_contact_meanssb.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;

import java.time.LocalDateTime;

/**
 * {@link AuditableEntity} class.
 * <p>
 * <strong>Entity</strong> - Represents an Auditable in the database.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Entity (ORM)
 * @upgrade 24/07/19
 * @since 23/06/10
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuditableEntity {

	public static final String ATTR_CREATED_AT = "created_at";
	public static final String ATTR_CREATED_BY = "created_by";
	public static final String ATTR_UPDATED_AT = "updated_at";
	public static final String ATTR_UPDATED_BY = "updated_by";

	/**
	 * created at attribute
	 */
	@Column(name = ATTR_CREATED_AT, nullable = false)
	private LocalDateTime createdAt;

	/**
	 * created by attribute
	 */
	@Column(name = ATTR_CREATED_BY, nullable = false)
	private String createdBy;

	/**
	 * updated at attribute
	 */
	@Column(name = ATTR_UPDATED_AT, nullable = false)
	private LocalDateTime updatedAt;

	/**
	 * updated by attribute
	 */
	@Column(name = ATTR_UPDATED_BY, nullable = false)
	private String updatedBy;

	/**
	 * method to provide certain functionality before creation
	 */
	@PrePersist
	public void prePersist () {
		this.createdAt = LocalDateTime.now();
	}

	/**
	 * method to update certain functionality before creation
	 */
	@PreUpdate
	public void preUpdate () {
		this.updatedAt = LocalDateTime.now();
	}
}
