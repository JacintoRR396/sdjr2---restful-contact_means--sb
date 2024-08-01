package com.sdjr2.rest_contact_meanssb.repositories.auth;

import com.sdjr2.rest_contact_meanssb.models.entities.auth.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * {@link RoleJpaRepository} class.
 * <p>
 * <strong>Repository (DAO)</strong> - Represents an interface for CRUD operations on a repository JPA for a
 * {@link RoleEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Repository (DAO)
 * @upgrade 24/08/01
 * @since 24/07/31
 */
@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {
  Optional<RoleEntity> findByName(String name);
}
