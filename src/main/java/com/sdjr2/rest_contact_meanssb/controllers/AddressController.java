package com.sdjr2.rest_contact_meanssb.controllers;

import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.services.AddressService;
import com.sdjr2.sb.library_commons.config.BaseHandlerLogger;
import com.sdjr2.sb.library_commons.controllers.BaseController;
import com.sdjr2.sb.library_commons.controllers.BaseControllerHelper;
import com.sdjr2.sb.library_commons.models.dto.search.SearchBodyDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
 * It uses the classes : <br> 01. Level Access : the dto {@link AddressDTO} <br> 02. Level Logic : the service
 * {@link AddressService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Controller
 * @upgrade 24/08/16
 * @since 23/06/10
 */
@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController implements BaseController<AddressDTO> {

	/**
	 * Address logger object
	 */
	BaseHandlerLogger logger = new BaseHandlerLogger( AddressController.class );

	/**
	 * Address service object
	 */
	private final AddressService addressService;

	@GetMapping
	public ResponseEntity<List<AddressDTO>> getAll ( HttpServletRequest httpServletRequest ) {
		List<AddressDTO> res = this.addressService.getAll();
		this.logger.infoResponse( BaseControllerHelper.formatLogContentResp( res.toString() ) );

		return new ResponseEntity<>( res, HttpStatus.OK );
	}

	@GetMapping(value = "/pagination")
	public ResponseEntity<Page<AddressDTO>> getAllWithPagination ( HttpServletRequest httpServletRequest,
																																 @RequestParam(defaultValue = "0", required = false) Integer offset,
																																 @RequestParam(defaultValue = "5", required = false) Integer limit ) {
		Page<AddressDTO> res = this.addressService.getAllWithPagination( offset, limit );
		this.logger.infoResponse( BaseControllerHelper.formatLogPageResp( res ) );

		return new ResponseEntity<>( res, HttpStatus.OK );
	}

	@GetMapping(value = "/search")
	public ResponseEntity<Page<AddressDTO>> getAllWithSearch ( HttpServletRequest httpServletRequest,
																														 @Valid @RequestBody SearchBodyDTO searchBodyDTO,
																														 BindingResult resValidation ) {
		this.logger.infoRequest( BaseControllerHelper.formatLogBodyReq( searchBodyDTO.toString() ) );
		this.checkValidation( resValidation );

		Page<AddressDTO> res = this.addressService.getAllWithSearch( searchBodyDTO );
		this.logger.infoResponse( BaseControllerHelper.formatLogPageResp( res ) );

		return new ResponseEntity<>( res, HttpStatus.OK );
	}

	@GetMapping(value = "/{addressId}")
	public ResponseEntity<AddressDTO> getOneById ( @PathVariable("addressId") Long id ) {
		AddressDTO res = this.addressService.getOneById( id );
		this.logger.infoResponse( BaseControllerHelper.formatLogContentResp( res.toString() ) );

		return new ResponseEntity<>( res, HttpStatus.OK );
	}

	@PostMapping
	public ResponseEntity<AddressDTO> create ( @Valid @RequestBody AddressDTO addressDTO, BindingResult resValidation ) {
		this.logger.infoRequest( BaseControllerHelper.formatLogBodyReq( addressDTO.toString() ) );
		this.checkValidation( resValidation );

		AddressDTO res = this.addressService.create( addressDTO );
		this.logger.infoResponse( BaseControllerHelper.formatLogContentResp( res.toString() ) );

		return new ResponseEntity<>( res, HttpStatus.CREATED );
	}

	@PutMapping(value = "/{addressId}")
	public ResponseEntity<AddressDTO> update ( @PathVariable("addressId") Long id,
																						 @Valid @RequestBody AddressDTO addressDTO, BindingResult resValidation ) {
		this.logger.infoRequest( BaseControllerHelper.formatLogBodyReq( addressDTO.toString() ) );
		this.checkValidation( resValidation );

		AddressDTO res = this.addressService.update( id, addressDTO );
		this.logger.infoResponse( BaseControllerHelper.formatLogContentResp( res.toString() ) );

		return new ResponseEntity<>( res, HttpStatus.OK );
	}

	@DeleteMapping(value = "/{addressId}")
	public ResponseEntity<Void> delete ( @PathVariable("addressId") Long id ) {
		this.addressService.delete( id );

		return new ResponseEntity<>( HttpStatus.NO_CONTENT );
	}
}
