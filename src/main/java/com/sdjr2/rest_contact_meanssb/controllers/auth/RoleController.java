package com.sdjr2.rest_contact_meanssb.controllers.auth;

import com.sdjr2.rest_contact_meanssb.models.dto.auth.RoleDTO;
import com.sdjr2.rest_contact_meanssb.services.auth.RoleService;
import com.sdjr2.sb.library_commons.controllers.BaseController;
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
 * {@link RoleController} class.
 * <p>
 * <strong>Controller</strong> - Represents a handler of request about Roles, this implements to
 * {@link BaseController}.
 * <p>
 * It uses the classes : <br> 01. Level Access : the dto {@link RoleDTO} <br> 02. Level Logic : the service
 * {@link RoleService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Controller
 * @upgrade 24/08/11
 * @since 24/08/01
 */
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController implements BaseController<RoleDTO> {

	/**
	 * Role service object
	 */
	private final RoleService roleService;

	@GetMapping
	public ResponseEntity<List<RoleDTO>> getAll ( HttpServletRequest httpServletRequest ) {
		return new ResponseEntity<>( this.roleService.getAll(), HttpStatus.OK );
	}

	@GetMapping(value = "/pagination")
	public ResponseEntity<Page<RoleDTO>> getAllWithPagination ( HttpServletRequest httpServletRequest,
																															@RequestParam(defaultValue = "0", required = false) Integer offset,
																															@RequestParam(defaultValue = "5", required = false) Integer limit ) {
		return new ResponseEntity<>( this.roleService.getAllWithPagination( offset, limit ), HttpStatus.OK );
	}

	@GetMapping(value = "/search")
	public ResponseEntity<Page<RoleDTO>> getAllWithSearch ( HttpServletRequest httpServletRequest,
																													@Valid @RequestBody SearchBodyDTO searchBodyDTO,
																													BindingResult resValidation ) {
		this.checkValidation( resValidation );

		return new ResponseEntity<>( this.roleService.getAllWithSearch( searchBodyDTO ), HttpStatus.OK );
	}

	@GetMapping(value = "/{roleId}")
	public ResponseEntity<RoleDTO> getOneById ( @PathVariable("roleId") Long id ) {
		return new ResponseEntity<>( this.roleService.getOneById( id ), HttpStatus.OK );
	}

	@PostMapping
	public ResponseEntity<RoleDTO> create ( @Valid @RequestBody RoleDTO dto, BindingResult resValidation ) {
		this.checkValidation( resValidation );

		return new ResponseEntity<>( this.roleService.create( dto ), HttpStatus.CREATED );
	}

	@PutMapping(value = "/{roleId}")
	public ResponseEntity<RoleDTO> update ( @PathVariable("roleId") Long id,
																					@Valid @RequestBody RoleDTO dto, BindingResult resValidation ) {
		this.checkValidation( resValidation );

		return new ResponseEntity<>( this.roleService.update( id, dto ), HttpStatus.OK );
	}

	@DeleteMapping(value = "/{roleId}")
	public ResponseEntity<Void> delete ( @PathVariable("roleId") Long id ) {
		this.roleService.delete( id );

		return new ResponseEntity<>( HttpStatus.NO_CONTENT );
	}
}
