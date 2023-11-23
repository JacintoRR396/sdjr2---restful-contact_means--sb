package com.sdjr2.rest_contact_meanssb.controller;

import com.sdjr2.rest_contact_meanssb.model.dto.AddressDtoReq;
import com.sdjr2.rest_contact_meanssb.model.entity.AddressEntity;
import com.sdjr2.rest_contact_meanssb.service.IAddressService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * {@link AddressController} class.
 * <p>
 * Controller - Represents a handler of request about Addresses.
 * <p>
 * It uses the dto request {@link AddressDtoReq}, the service {@link IAddressService} and the entity {@link AddressEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Controller
 * @since 23/06/10
 * @upgrade 23/06/11
 */
@RestController
@RequestMapping("/addresses")
public class AddressController {

    /**
     * Validator object
     */
    @Autowired
    private Validator validator;

    /**
     * httpRequest object
     */
    @Autowired
    private HttpServletRequest httpRequest;

    /**
     * Address service object
     */
    @Autowired
    @Qualifier("addressServiceMock")
    private IAddressService addressService;

    /*********** GET ALL ***********/
    @GetMapping
    public ResponseEntity<List<AddressEntity>> getAddresses() {
        return new ResponseEntity<>(this.addressService.getAddresses(), HttpStatus.OK);
    }

    @GetMapping(value = "/pagination")
    public ResponseEntity<Page<AddressEntity>> getAddressesWithPagination(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
            @RequestParam(value = "size", defaultValue = "15", required = false) Integer pageSize) {
        return new ResponseEntity<>(this.addressService.getAddressesWithPagination(pageNum, pageSize), HttpStatus.OK);
    }

    @GetMapping(value = "/order")
    public ResponseEntity<List<AddressEntity>> getAddressesWithOrder(
            @RequestParam(value = "attribute", defaultValue = "postalCode", required = false) String attribute,
            @RequestParam(value = "asc", defaultValue = "true", required = false) boolean isAsc) {
        return new ResponseEntity<>(this.addressService.getAddressesWithOrder(attribute, isAsc), HttpStatus.OK);
    }

    @GetMapping(value = "/paginationAndOrder")
    public ResponseEntity<Page<AddressEntity>> getAddressesWithPaginationAndOrder(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
            @RequestParam(value = "size", defaultValue = "15", required = false) Integer pageSize,
            @RequestParam(value = "attribute", defaultValue = "postalCode", required = false) String attribute,
            @RequestParam(value = "asc", defaultValue = "true", required = false) boolean isAsc) {
        return new ResponseEntity<>(this.addressService.getAddressesWithPaginationAndOrder(pageNum, pageSize, attribute, isAsc), HttpStatus.OK);
    }

    /*********** GET ***********/
    @GetMapping(value = "/{addressId}")
    public ResponseEntity<AddressEntity> getAddressById(@PathVariable("addressId") Integer id) {
        return new ResponseEntity<>(this.addressService.getAddressById(id), HttpStatus.OK);
    }

    /*********** POST ***********/
    @PostMapping
    public ResponseEntity<AddressEntity> addAddress(@RequestBody @Valid AddressDtoReq addressDtoReq) {
        if (this.validateRequest(this.validator.validate(addressDtoReq))) {
            return new ResponseEntity<>(this.addressService.createAddress(addressDtoReq), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*********** PUT ***********/
    @PutMapping(value = "/{addressId}")
    public ResponseEntity<AddressEntity> updateAddress(@PathVariable("addressId") Integer id,
                                                       @RequestBody @Valid AddressDtoReq addressDtoReq) {
        if (this.validateRequest(this.validator.validate(addressDtoReq))) {
            return new ResponseEntity<>(this.addressService.updateAddress(id, addressDtoReq), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*********** DELETE ***********/
    @DeleteMapping(value = "/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("addressId") Integer id) {
        this.addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Check if validation is empty
     *
     * @param validation set of ConstraintViolation object
     * @return a {@link boolean} object.
     */
    private boolean validateRequest(Set<?> validation) {
        return validation.isEmpty();
    }

}
