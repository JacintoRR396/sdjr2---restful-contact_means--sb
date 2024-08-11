package com.sdjr2.rest_contact_meanssb.services.auth;

import com.sdjr2.rest_contact_meanssb.models.dto.auth.UserDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.auth.UserEntity;
import com.sdjr2.rest_contact_meanssb.models.mappers.auth.UserMapper;
import com.sdjr2.rest_contact_meanssb.repositories.auth.UserJpaRepository;
import com.sdjr2.sb.library_commons.services.BaseService;

/**
 * {@link UserService} interface.
 * <p>
 * <strong>Service</strong> - Represents an interface service that manages business logic about Users, this
 * extends from {@link BaseService}.
 * <p>
 * This Service maps the users of the request {@link UserDTO} to database {@link UserEntity} with {@link UserMapper}.
 * <br>
 * It uses the classes : <br> 01. Level Access : the dto {@link UserDTO} <br> 02. Level Logic : the mapper
 * {@link UserMapper} <br> 03. Level Data : the repo {@link UserJpaRepository} and the entity {@link UserEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/08/01
 * @since 24/08/01
 */
public interface UserService extends BaseService<UserDTO> {

	/**
	 * Gets an element dto for its unique attrs, otherwise throw an exception STATUS_40402.
	 *
	 * @param username first unique attribute.
	 * @return an element dto {@link UserDTO}.
	 */
	UserDTO getOneByUserName ( String username );
}
