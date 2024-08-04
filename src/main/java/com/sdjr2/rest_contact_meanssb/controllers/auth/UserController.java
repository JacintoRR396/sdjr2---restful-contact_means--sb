package com.sdjr2.rest_contact_meanssb.controllers.auth;

import com.sdjr2.rest_contact_meanssb.controllers.BaseController;
import com.sdjr2.rest_contact_meanssb.models.dto.auth.UserDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.SearchBodyDTO;
import com.sdjr2.rest_contact_meanssb.services.auth.UserService;
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
 * {@link UserController} class.
 * <p>
 * <strong>Controller</strong> - Represents a handler of request about Users, this implements to
 * {@link BaseController}.
 * <p>
 * It uses the classes : <br> 01. Level Access : the dto {@link UserDTO} <br> 02. Level Logic : the service
 * {@link UserService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Controller
 * @upgrade 24/08/02
 * @since 24/08/02
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements BaseController<UserDTO> {

	/**
	 * User service object
	 */
	private final UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> getAll ( HttpServletRequest httpServletRequest ) {
		return new ResponseEntity<>( this.userService.getAll(), HttpStatus.OK );
	}

	@GetMapping(value = "/pagination")
	public ResponseEntity<Page<UserDTO>> getAllWithPagination ( HttpServletRequest httpServletRequest,
																															@RequestParam(defaultValue = "0", required = false) Integer offset,
																															@RequestParam(defaultValue = "5", required = false) Integer limit ) {
		return new ResponseEntity<>( this.userService.getAllWithPagination( offset, limit ), HttpStatus.OK );
	}

	@GetMapping(value = "/search")
	public ResponseEntity<Page<UserDTO>> getAllWithSearch ( HttpServletRequest httpServletRequest,
																													@Valid @RequestBody SearchBodyDTO searchBodyDTO,
																													BindingResult resValidation ) {
		this.checkValidation( resValidation );

		return new ResponseEntity<>( this.userService.getAllWithSearch( searchBodyDTO ), HttpStatus.OK );
	}

	@GetMapping(value = "/{userId}")
	public ResponseEntity<UserDTO> getOneById ( @PathVariable("userId") Long id ) {
		return new ResponseEntity<>( this.userService.getOneById( id ), HttpStatus.OK );
	}

	@PostMapping
	public ResponseEntity<UserDTO> create ( @Valid @RequestBody UserDTO dto, BindingResult resValidation ) {
		this.checkValidation( resValidation );

		return new ResponseEntity<>( this.userService.create( dto ), HttpStatus.CREATED );
	}

	@PutMapping(value = "/{userId}")
	public ResponseEntity<UserDTO> update ( @PathVariable("userId") Long id,
																					@Valid @RequestBody UserDTO dto, BindingResult resValidation ) {
		this.checkValidation( resValidation );

		return new ResponseEntity<>( this.userService.update( id, dto ), HttpStatus.OK );
	}

	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<Void> delete ( @PathVariable("userId") Long id ) {
		this.userService.delete( id );

		return new ResponseEntity<>( HttpStatus.NO_CONTENT );
	}
}
