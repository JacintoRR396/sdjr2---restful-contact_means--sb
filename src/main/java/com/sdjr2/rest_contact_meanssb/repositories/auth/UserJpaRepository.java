package com.sdjr2.rest_contact_meanssb.repositories.auth;

import com.sdjr2.rest_contact_meanssb.models.entities.auth.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * {@link UserJpaRepository} class.
 * <p>
 * <strong>Repository (DAO)</strong> - Represents an interface for CRUD operations on a repository JPA for a
 * {@link UserEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Repository (DAO)
 * @upgrade 24/08/01
 * @since 24/07/31
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long>,
		JpaSpecificationExecutor<UserEntity> {
	Optional<UserEntity> findByUsername ( String username );
}
