package com.sdjr2.rest_contact_meanssb.models.validations;

import com.sdjr2.rest_contact_meanssb.repositories.auth.UserJpaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserNotExistsByUsernameValidator implements ConstraintValidator<UserNotExistsByUsername, String> {

	/**
	 * User repository object
	 */
	private final UserJpaRepository userRepo;

	@Override
	public boolean isValid ( String value, ConstraintValidatorContext context ) {
		if ( Objects.isNull( this.userRepo ) ) return true;

		return this.userRepo.findByUsername( value ).isEmpty();
	}
}
