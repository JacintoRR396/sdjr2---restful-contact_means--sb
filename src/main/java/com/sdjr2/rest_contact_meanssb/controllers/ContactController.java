package com.sdjr2.rest_contact_meanssb.controllers;

import com.sdjr2.rest_contact_meanssb.models.dto.ContactDTO;
import com.sdjr2.rest_contact_meanssb.services.ContactService;
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
 * {@link ContactController} class.
 * <p>
 * <strong>Controller</strong> - Represents a handler of request about Contacts, this implements to
 * {@link BaseController}.
 * <p>
 * It uses the classes : <br> 01. Level Access : the dto {@link ContactDTO} <br> 02. Level Logic : the service
 * {@link ContactService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Controller
 * @upgrade 24/08/16
 * @since 24/08/12
 */
@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController implements BaseController<ContactDTO> {

	/**
	 * Contact logger object
	 */
	BaseHandlerLogger logger = new BaseHandlerLogger( ContactController.class );

	/**
	 * Contact service object
	 */
	private final ContactService contactService;

	@GetMapping
	public ResponseEntity<List<ContactDTO>> getAll ( HttpServletRequest httpServletRequest ) {
		List<ContactDTO> res = this.contactService.getAll();
		this.logger.infoResponse( BaseControllerHelper.formatLogContentResp( res.toString() ) );

		return new ResponseEntity<>( res, HttpStatus.OK );
	}

	@GetMapping(value = "/pagination")
	public ResponseEntity<Page<ContactDTO>> getAllWithPagination ( HttpServletRequest httpServletRequest,
																																 @RequestParam(defaultValue = "0", required = false) Integer offset,
																																 @RequestParam(defaultValue = "5", required = false) Integer limit ) {
		Page<ContactDTO> res = this.contactService.getAllWithPagination( offset, limit );
		this.logger.infoResponse( BaseControllerHelper.formatLogPageResp( res ) );

		return new ResponseEntity<>( res, HttpStatus.OK );
	}

	@GetMapping(value = "/search")
	public ResponseEntity<Page<ContactDTO>> getAllWithSearch ( HttpServletRequest httpServletRequest,
																														 @Valid @RequestBody SearchBodyDTO searchBodyDTO,
																														 BindingResult resValidation ) {
		this.logger.infoRequest( BaseControllerHelper.formatLogBodyReq( searchBodyDTO.toString() ) );
		this.checkValidation( resValidation );

		Page<ContactDTO> res = this.contactService.getAllWithSearch( searchBodyDTO );
		this.logger.infoResponse( BaseControllerHelper.formatLogPageResp( res ) );

		return new ResponseEntity<>( res, HttpStatus.OK );
	}

	@GetMapping(value = "/{contactId}")
	public ResponseEntity<ContactDTO> getOneById ( @PathVariable("contactId") Long id ) {
		ContactDTO res = this.contactService.getOneById( id );
		this.logger.infoResponse( BaseControllerHelper.formatLogContentResp( res.toString() ) );

		return new ResponseEntity<>( res, HttpStatus.OK );
	}

	@PostMapping
	public ResponseEntity<ContactDTO> create ( @Valid @RequestBody ContactDTO contactDTO, BindingResult resValidation ) {
		this.logger.infoRequest( BaseControllerHelper.formatLogBodyReq( contactDTO.toString() ) );
		this.checkValidation( resValidation );

		ContactDTO res = this.contactService.create( contactDTO );
		this.logger.infoResponse( BaseControllerHelper.formatLogContentResp( res.toString() ) );

		return new ResponseEntity<>( res, HttpStatus.CREATED );
	}

	@PutMapping(value = "/{contactId}")
	public ResponseEntity<ContactDTO> update ( @PathVariable("contactId") Long id,
																						 @Valid @RequestBody ContactDTO contactDTO, BindingResult resValidation ) {
		this.logger.infoRequest( BaseControllerHelper.formatLogBodyReq( contactDTO.toString() ) );
		this.checkValidation( resValidation );

		ContactDTO res = this.contactService.update( id, contactDTO );
		this.logger.infoResponse( BaseControllerHelper.formatLogContentResp( res.toString() ) );

		return new ResponseEntity<>( res, HttpStatus.OK );
	}

	@DeleteMapping(value = "/{contactId}")
	public ResponseEntity<Void> delete ( @PathVariable("contactId") Long id ) {
		this.contactService.delete( id );

		return new ResponseEntity<>( HttpStatus.NO_CONTENT );
	}
}