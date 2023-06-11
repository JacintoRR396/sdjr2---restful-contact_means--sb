package com.sdjr2.rest_contact_meanssb.repository;

import com.sdjr2.rest_contact_meanssb.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link IAddressRepository} class.
 * <p>
 * DAO - Represents an interface for CRUD operations on a repository JPA for a {@link AddressEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Repository (DAO)
 * @since 23/06/10
 * @upgrade 23/06/10
 */
@Repository
public interface IAddressRepository extends JpaRepository<AddressEntity, Integer> {

    @Query("SELECT DISTINCT a.town FROM AddressEntity a")
    List<String> findAllTowns();

}
