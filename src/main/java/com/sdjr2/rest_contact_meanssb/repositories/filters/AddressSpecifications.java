package com.sdjr2.rest_contact_meanssb.repositories.filters;

import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * {@link AddressSpecifications} class.
 * <p>
 * <strong>Repository (DAO)</strong> - Represents a class with the address specifications.
 * <br>
 * It uses the classes : <br> 01. Level Data -> the generic specifications {@link BaseSpecifications}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Repository (DAO)
 * @upgrade 24/08/01
 * @since 24/07/18
 */
@Component
@NoArgsConstructor
public class AddressSpecifications extends BaseSpecifications<AddressEntity> {
}
