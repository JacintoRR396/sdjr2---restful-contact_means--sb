package com.sdjr2.rest_contact_meanssb.repositories;

import java.util.List;
import java.util.Optional;

/**
 * {@link AddressRepository} class.
 * <p>
 * <strong>Repository</strong> - Represents a common interface for different suppliers.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Repository (DAO)
 * @upgrade 24/06/18
 * @since 24/06/14
 */
public interface AddressRepository<T, I> {

  List<T> findAll();

  Optional<T> findById(I id);

  T save(T entity);
}
