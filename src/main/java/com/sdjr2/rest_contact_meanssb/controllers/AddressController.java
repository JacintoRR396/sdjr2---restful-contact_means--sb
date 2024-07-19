package com.sdjr2.rest_contact_meanssb.controllers;

import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.PaginationDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.SearchBodyDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.services.AddressService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * {@link AddressController} class.
 * <p>
 * <strong>Controller</strong> - Represents a handler of request about Addresses, this implements to
 * {@link BaseController}.
 * <p>
 * It uses the classes : <br> 01. Level Access -> the dto {@link AddressDTO} <br> 02. Level Logic -> the service
 * {@link AddressService} <br> 03. Level Data -> the entity {@link AddressEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Controller
 * @upgrade 24/07/17
 * @see Validator Validator validates all constraints.
 * @see HttpServletRequest HttpServletRequest provides request information for HTTP servlets.
 * @since 23/06/10
 */
@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController implements BaseController<AddressDTO> {

	/**
	 * Address service object
	 */
	private final AddressService addressService;

	/**
	 * Handler method to perform a GET operation on a collection with address dto.
	 *
	 * @param httpServletRequest http servlet request.
	 * @return a response {@link ResponseEntity} with a collection {@link List} of address dto {@link AddressDTO}.
	 */
	@GetMapping
	public ResponseEntity<List<AddressDTO>> getAll ( HttpServletRequest httpServletRequest ) {
		return new ResponseEntity<>( this.addressService.getAll(), HttpStatus.OK );
	}

	/**
	 * Handler method to perform a GET operation on a collection with address dto through pagination.
	 *
	 * @param httpServletRequest http servlet request.
	 * @param offset             index of the page to obtain.
	 * @param limit              limit of values to obtain.
	 * @return a response {@link ResponseEntity} with a pagination {@link Page} of address dto {@link AddressDTO}.
	 */
	@GetMapping(value = "/pagination")
	public ResponseEntity<Page<AddressDTO>> getAllWithPagination ( HttpServletRequest httpServletRequest,
																																 @RequestParam(defaultValue = "0", required = false) Integer offset,
																																 @RequestParam(defaultValue = "5", required = false) Integer limit ) {
		return new ResponseEntity<>( this.addressService.getAllWithPagination( offset, limit ), HttpStatus.OK );
	}

	/**
	 * Handler method to perform a GET operation on a collection with address dto through search.
	 *
	 * @param httpServletRequest http servlet request.
	 * @param searchBodyDTO      dto with search parameters.
	 * @param resValidation      binding result about validations.
	 * @return a response {@link ResponseEntity} with a pagination {@link PaginationDTO} of elements dto
	 *    {@link AddressDTO}.
	 */
	@GetMapping(value = "/search")
	public ResponseEntity<Page<AddressDTO>> getAllWithSearch ( HttpServletRequest httpServletRequest,
																																			@Valid @RequestBody SearchBodyDTO searchBodyDTO,
																																			BindingResult resValidation ) {
		this.checkValidation( resValidation );

		return new ResponseEntity<>( this.addressService.getAllWithSearch( searchBodyDTO ), HttpStatus.OK );
	}

	/*********** GET ***********/
	@GetMapping(value = "/{addressId}")
	public ResponseEntity<AddressDTO> getOneById ( @PathVariable("addressId") Integer id ) {
		return new ResponseEntity<>( this.addressService.getOneById( id ), HttpStatus.OK );
	}

	/*********** POST ***********/
	@PostMapping
	public ResponseEntity<AddressDTO> create ( @Valid @RequestBody AddressDTO addressDTO, BindingResult resValidation ) {
		this.checkValidation( resValidation );

		return new ResponseEntity<>( this.addressService.create( addressDTO ), HttpStatus.CREATED );
	}

	/*********** PUT ***********/
	@PutMapping(value = "/{addressId}")
	public ResponseEntity<AddressDTO> update ( @Valid @RequestBody AddressDTO addressDTO, BindingResult resValidation,
																						 @PathVariable("addressId") Integer id ) {
		this.checkValidation( resValidation );

		return new ResponseEntity<>( this.addressService.update( addressDTO, id ), HttpStatus.OK );
	}

	/*********** DELETE ***********/
	@DeleteMapping(value = "/{addressId}")
	public ResponseEntity<Void> delete ( @PathVariable("addressId") Integer id ) {
		this.addressService.delete( id );

		return new ResponseEntity<>( HttpStatus.NO_CONTENT );
	}

	@GetMapping(value = "/order")
	public ResponseEntity<List<AddressEntity>> getAddressesWithOrder (
			@RequestParam(value = "attribute", defaultValue = "postalCode", required = false) String attribute,
			@RequestParam(value = "asc", defaultValue = "true", required = false) boolean isAsc ) {
		return new ResponseEntity<>( this.addressService.getAddressesWithOrder( attribute, isAsc ), HttpStatus.OK );
	}

	@GetMapping(value = "/paginationAndOrder")
	public ResponseEntity<Page<AddressEntity>> getAddressesWithPaginationAndOrder (
			@RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
			@RequestParam(value = "size", defaultValue = "15", required = false) Integer pageSize,
			@RequestParam(value = "attribute", defaultValue = "postalCode", required = false) String attribute,
			@RequestParam(value = "asc", defaultValue = "true", required = false) boolean isAsc ) {
		return new ResponseEntity<>(
				this.addressService.getAddressesWithPaginationAndOrder( pageNum, pageSize, attribute, isAsc ), HttpStatus.OK );
	}
}
