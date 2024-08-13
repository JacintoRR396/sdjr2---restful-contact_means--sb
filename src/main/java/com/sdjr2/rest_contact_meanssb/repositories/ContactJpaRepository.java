package com.sdjr2.rest_contact_meanssb.repositories;

import com.sdjr2.rest_contact_meanssb.models.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * {@link ContactJpaRepository} class.
 * <p>
 * <strong>Repository (DAO)</strong> - Represents an interface for CRUD operations on a repository JPA for a
 * {@link ContactEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Repository (DAO)
 * @upgrade 24/08/12
 * @since 24/08/12
 */
@Repository
public interface ContactJpaRepository extends JpaRepository<ContactEntity, Long>,
		JpaSpecificationExecutor<ContactEntity> {
	Optional<ContactEntity> findByEmail ( String email );
}
