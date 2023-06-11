package com.sdjr2.rest_contact_meanssb.model.entity;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * {@link AuditableEntity} class.
 * <p>
 * ORM - Represents an Auditable in the database.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Entity (ORM)
 * @since 23/06/10
 * @upgrade 23/06/10
 */
@NoArgsConstructor
@Getter
@Setter
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
