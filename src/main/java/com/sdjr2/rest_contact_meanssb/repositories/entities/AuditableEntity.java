package com.sdjr2.rest_contact_meanssb.repositories.entities;

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
 * @upgrade 24/06/18
 * @since 23/06/10
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuditableEntity {

  /**
   * created at attribute
   */
  @Column(name = "created_at_dat", nullable = false)
  private LocalDateTime createdAt;

  /**
   * created by attribute
   */
  @Column(name = "created_by_str", nullable = false)
  private String createdBy;

  /**
   * updated at attribute
   */
  @Column(name = "updated_at_dat", nullable = false)
  private LocalDateTime updatedAt;

  /**
   * updated by attribute
   */
  @Column(name = "updated_by_str", nullable = false)
  private String updatedBy;

  /**
   * method to provide certain functionality before creation
   */
  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDateTime.now();
  }

  /**
   * method to update certain functionality before creation
   */
  @PreUpdate
  public void preUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
