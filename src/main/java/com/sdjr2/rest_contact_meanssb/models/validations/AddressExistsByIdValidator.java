package com.sdjr2.rest_contact_meanssb.models.validations;

import com.sdjr2.rest_contact_meanssb.repositories.AddressJpaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AddressExistsByIdValidator implements ConstraintValidator<AddressExistsById, Long> {

	/**
	 * Address repository object
	 */
	private final AddressJpaRepository repo;

	@Override
	public boolean isValid ( Long value, ConstraintValidatorContext context ) {
		if ( Objects.isNull( this.repo ) ) return true;
		// 1º about create, 2º about update
		return Objects.isNull( value )
				|| value == 0L || this.repo.findById( value ).isPresent();
	}
}
