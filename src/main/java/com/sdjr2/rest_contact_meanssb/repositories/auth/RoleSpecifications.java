package com.sdjr2.rest_contact_meanssb.repositories.auth;

import com.sdjr2.rest_contact_meanssb.models.entities.auth.RoleEntity;
import com.sdjr2.rest_contact_meanssb.repositories.filters.BaseSpecifications;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * {@link RoleSpecifications} class.
 * <p>
 * <strong>Repository (DAO)</strong> - Represents a class with the role specifications.
 * <br>
 * It uses the classes : <br> 01. Level Data -> the generic specifications {@link BaseSpecifications}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Repository (DAO)
 * @upgrade 24/08/01
 * @since 24/08/01
 */
@Component
@NoArgsConstructor
public class RoleSpecifications extends BaseSpecifications<RoleEntity> {
}
