package com.sdjr2.rest_contact_meanssb.repositories.entities;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * {@link AuditableEntity} class.
 * <p>
 * <strong>Entity</strong> - Represents an Auditable in the database.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Entity (ORM)
 * @upgrade 24/03/28
 * @since 23/06/10
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuditableEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 2339186471035380213L;

	/**
	 * created at attribute
	 */
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	/**
	 * created by attribute
	 */
	@Column(name = "created_by", nullable = false)
	private String createdBy;

	/**
	 * updated at attribute
	 */
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	/**
	 * updated by attribute
	 */
	@Column(name = "updated_by", nullable = false)
	private String updatedBy;
}
