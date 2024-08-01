package com.sdjr2.rest_contact_meanssb.services.auth;

import com.sdjr2.rest_contact_meanssb.models.dto.auth.RoleDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.auth.RoleEntity;
import com.sdjr2.rest_contact_meanssb.models.mappers.auth.RoleMapper;
import com.sdjr2.rest_contact_meanssb.repositories.auth.RoleJpaRepository;
import com.sdjr2.rest_contact_meanssb.services.BaseService;

/**
 * {@link RoleService} interface.
 * <p>
 * <strong>Service</strong> - Represents an interface service that manages business logic about Roles, this
 * extends from {@link BaseService}.
 * <p>
 * This Service maps the roles of the request {@link RoleDTO} to database {@link RoleEntity} with {@link RoleMapper}.
 * <br>
 * It uses the classes : <br> 01. Level Access : the dto {@link RoleDTO} <br> 02. Level Logic : the mapper
 * {@link RoleMapper} <br> 03. Level Data : the repo {@link RoleJpaRepository} and the entity {@link RoleEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/08/01
 * @since 24/08/01
 */
public interface RoleService extends BaseService<RoleDTO> {
}
