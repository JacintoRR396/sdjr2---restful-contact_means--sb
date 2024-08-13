package com.sdjr2.rest_contact_meanssb.repositories.filters;

import com.sdjr2.rest_contact_meanssb.models.entities.ContactEntity;
import com.sdjr2.sb.library_commons.repositories.filters.BaseSpecifications;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * {@link ContactSpecifications} class.
 * <p>
 * <strong>Repository (DAO)</strong> - Represents a class with the contact specifications.
 * <br>
 * It uses the classes : <br> 01. Level Data -> the generic specifications {@link BaseSpecifications}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Repository (DAO)
 * @upgrade 24/08/12
 * @since 24/08/12
 */
@Component
@NoArgsConstructor
public class ContactSpecifications extends BaseSpecifications<ContactEntity> {
}
