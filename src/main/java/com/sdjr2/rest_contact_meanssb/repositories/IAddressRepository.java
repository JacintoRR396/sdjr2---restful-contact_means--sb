package com.sdjr2.rest_contact_meanssb.repositories;

import com.sdjr2.rest_contact_meanssb.repositories.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link IAddressRepository} class.
 * <p>
 * <strong>Repository</strong> - Represents an interface for CRUD operations on a repository JPA for a
 * {@link AddressEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Repository (DAO)
 * @upgrade 24/06/11
 * @since 23/06/10
 */
@Repository
public interface IAddressRepository extends JpaRepository<AddressEntity, Integer> {

	@Query("SELECT DISTINCT a.town FROM AddressEntity a")
	List<String> findAllTowns();
}
