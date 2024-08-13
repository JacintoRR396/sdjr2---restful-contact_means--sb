package com.sdjr2.rest_contact_meanssb.services;

import com.sdjr2.rest_contact_meanssb.models.dto.ContactDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.ContactEntity;
import com.sdjr2.rest_contact_meanssb.models.mappers.ContactMapper;
import com.sdjr2.rest_contact_meanssb.repositories.ContactJpaRepository;
import com.sdjr2.sb.library_commons.services.BaseService;

/**
 * {@link ContactService} interface.
 * <p>
 * <strong>Service</strong> - Represents an interface service that manages business logic about Contacts, this
 * extends from {@link BaseService}.
 * <p>
 * This Service maps the contacts of the request {@link ContactDTO} to database {@link ContactEntity} with
 * {@link ContactMapper}.
 * <br>
 * It uses the classes : <br> 01. Level Access : the dto {@link ContactDTO} <br> 02. Level Logic : the mapper
 * {@link ContactMapper} <br> 03. Level Data : the repo {@link ContactJpaRepository} and the entity
 * {@link ContactEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/08/12
 * @since 24/08/12
 */
public interface ContactService extends BaseService<ContactDTO> {
}
