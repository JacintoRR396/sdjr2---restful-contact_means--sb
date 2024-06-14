package com.sdjr2.rest_contact_meanssb.repositories.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdjr2.rest_contact_meanssb.repositories.AddressRepository;
import com.sdjr2.rest_contact_meanssb.repositories.entities.AddressEntity;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * {@link AddressRepositoryImpl} class.
 * <p>
 * <strong>Repository (DAO)</strong> - Represents an interface for CRUD operations on a mock repository for a
 * {@link AddressEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Repository (DAO)
 * @upgrade 24/06/14
 * @since 24/06/13
 */
public class AddressRepositoryImpl implements AddressRepository<AddressEntity, Integer> {

  private final List<AddressEntity> addresses;

  public AddressRepositoryImpl(Resource resource) {
    ObjectMapper objMapper = new ObjectMapper();
    try {
      this.addresses = Arrays.asList(objMapper.readValue(resource.getFile(), AddressEntity[].class));
      System.out.println("Load addresses from .json : " + this.addresses);
    } catch (IOException | NullPointerException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<AddressEntity> findAll() {
    return this.addresses;
  }

  @Override
  public Optional<AddressEntity> findById(Integer id) {
    return this.addresses.stream().filter(addressEntity -> addressEntity.getId().equals(id)).findFirst();
  }
}
