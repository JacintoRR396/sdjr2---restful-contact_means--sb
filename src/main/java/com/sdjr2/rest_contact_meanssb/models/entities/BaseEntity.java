package com.sdjr2.rest_contact_meanssb.models.entities;

/**
 * {@link BaseEntity} class.
 * <p>
 * <strong>Entity (ORM)</strong> - Interface with the common entity.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Entity (ORM)
 * @upgrade 24/08/01
 * @since 23/06/20
 */
public interface BaseEntity {
  AuditableEntity getAuditableEntity();
}
