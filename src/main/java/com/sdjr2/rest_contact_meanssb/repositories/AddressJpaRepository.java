package com.sdjr2.rest_contact_meanssb.repositories;

import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * {@link AddressJpaRepository} class.
 * <p>
 * <strong>Repository (DAO)</strong> - Represents an interface for CRUD operations on a repository JPA for a
 * {@link AddressEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Repository (DAO)
 * @upgrade 24/07/18
 * @since 23/06/10
 */
@Repository
public interface AddressJpaRepository extends JpaRepository<AddressEntity, Integer>,
		JpaSpecificationExecutor<AddressEntity> {

	@Query("SELECT DISTINCT a.town FROM AddressEntity a")
	List<String> findAllTowns ();

	Optional<AddressEntity> findByStreetAndNumberAndPostalCode ( String street, String number, String postalCode );
}
