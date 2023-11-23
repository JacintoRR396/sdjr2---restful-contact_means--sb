package com.sdjr2.rest_contact_meanssb.service.impl;

import com.sdjr2.rest_contact_meanssb.mapper.AddressDtoReqMapper;
import com.sdjr2.rest_contact_meanssb.model.dto.AddressDtoReq;
import com.sdjr2.rest_contact_meanssb.model.entity.AddressEntity;
import com.sdjr2.rest_contact_meanssb.repository.IAddressRepository;
import com.sdjr2.rest_contact_meanssb.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * {@link AddressServiceImpl} class.
 * <p>
 * Logic - Represents a service that implements to {@link IAddressService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @since 23/06/10
 * @upgrade 23/06/11
 */
@Service
@RequiredArgsConstructor
@Primary
@Qualifier("addressServiceMySQL")
public class AddressServiceImpl implements IAddressService {

    /**
     * Logger object
     */
    private static final Logger LOG = LoggerFactory.getLogger(AddressServiceImpl.class);

    /**
     * Address dto request mapper object
     */
    @Autowired
    private final AddressDtoReqMapper addressDtoReqMapper;

    /**
     * Address repository object
     */
    @Autowired
    private final IAddressRepository addressRepo;

    @Override
    public List<AddressEntity> getAddresses() {
        return this.addressRepo.findAll();
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

    /**
     * Gets a list of towns about addresses
     *
     * @return a list of {@link String} object.
     */
    public List<String> getTowns() {
        return this.addressRepo.findAllTowns();
    }

    @Override
    public AddressEntity getAddressById(Integer id) {
        return this.checkExistsAddress(id);
    }

    @Override
    public AddressEntity createAddress(AddressDtoReq addressDtoReq) {
        AddressEntity entity = this.addressDtoReqMapper.toEntity(addressDtoReq, true);
        try {
            this.checkExistsAddress(entity.getId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Address with ID '%d' already exists", entity.getId()));
        } catch (ResponseStatusException rse) {
            return this.addressRepo.save(entity);
        }
    }

    @Override
    public AddressEntity updateAddress(Integer id, AddressDtoReq addressDtoReq) {
        AddressEntity entity = this.addressDtoReqMapper.toEntity(addressDtoReq, false);
        this.checkExistsAddress(entity.getId());
        return this.addressRepo.save(entity);
    }

    @Override
    public void deleteAddress(Integer id) {
        AddressEntity entityDB = this.checkExistsAddress(id);
        this.addressRepo.delete(entityDB);
    }

    /**
     * Check if exists an address entity by its primary key
     *
     * @param id is the primary key.
     */
    private AddressEntity checkExistsAddress(final Integer id) {
        return this.addressRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Address with ID '%d' not found", id)));
    }

}
