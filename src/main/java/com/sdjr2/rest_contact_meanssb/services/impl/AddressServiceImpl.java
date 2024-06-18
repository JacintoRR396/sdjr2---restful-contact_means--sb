package com.sdjr2.rest_contact_meanssb.services.impl;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.mappers.AddressMapper;
import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.repositories.AddressJpaRepository;
import com.sdjr2.rest_contact_meanssb.repositories.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * {@link AddressServiceImpl} class.
 * <p>
 * <strong>Service</strong> - Represents a class service that implements to {@link AddressService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/06/18
 * @since 23/06/10
 */
@Service
@RequiredArgsConstructor
@Primary
public class AddressServiceImpl implements AddressService {

  /**
   * Address mapper object
   */
  private final AddressMapper addressMapper;

  /**
   * Address repository object
   */
  private final AddressJpaRepository addressRepo;

  @Override
  @Transactional(readOnly = true)
  public List<AddressDTO> getAddresses() {
    return this.addressRepo.findAll().stream().map(this.addressMapper::toDTO).toList();
  }

  @Override
  public Page<AddressEntity> getAddressesWithPagination(Integer pageNum, Integer pageSize) {
    return this.addressRepo.findAll(PageRequest.of(pageNum, pageSize));
  }

  @Override
  public List<AddressEntity> getAddressesWithOrder(String attribute, boolean isAsc) {
    final Sort.Direction typeOrder = (isAsc) ? Sort.Direction.ASC : Sort.Direction.DESC;
    return this.addressRepo.findAll(Sort.by(typeOrder, attribute));
  }

  @Override
  public Page<AddressEntity> getAddressesWithPaginationAndOrder(Integer pageNum, Integer pageSize,
                                                                String attribute, boolean isAsc) {
    final Sort.Direction typeOrder = (isAsc) ? Sort.Direction.ASC : Sort.Direction.DESC;
    return this.addressRepo.findAll(PageRequest.of(pageNum, pageSize, typeOrder, attribute));
  }

  public List<String> getTowns() {
    return this.addressRepo.findAllTowns();
  }

  @Override
  @Transactional(readOnly = true)
  public AddressDTO getAddressById(Integer id) {
    AddressEntity entity = this.checkExistsAddress(id);
    return this.addressMapper.toDTO(entity);
  }

  @Override
  public AddressDTO createAddress(AddressDTO addressDTO) {
    AddressEntity entityReq = this.addressMapper.toEntity(addressDTO, true);
    AddressEntity entityDB = this.addressRepo.save(entityReq);

    return this.addressMapper.toDTO( entityDB );
  }

  @Override
  public AddressEntity updateAddress(AddressDTO addressDTO, Integer id) {
    AddressEntity entity = this.addressMapper.toEntity(addressDTO, false);
    this.checkExistsAddress(entity.getId());
    return this.addressRepo.save(entity);
  }

  @Override
  public void deleteAddress(Integer id) {
    AddressEntity entityDB = this.checkExistsAddress(id);
    this.addressRepo.delete(entityDB);
  }

  private AddressEntity checkExistsAddress(final Integer id) {
    try {
      return this.addressRepo.findById(id).orElseThrow();
    } catch (NoSuchElementException ex) {
     throw new CustomException(ex, AppExceptionCodeEnum.STATUS_40400);
    }
  }
}
