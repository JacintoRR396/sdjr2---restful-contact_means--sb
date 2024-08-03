package com.sdjr2.rest_contact_meanssb.validations;

import com.sdjr2.rest_contact_meanssb.repositories.auth.UserJpaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserExistsByIdValidator implements ConstraintValidator<UserExistsById, Long> {

	/**
	 * User repository object
	 */
	private final UserJpaRepository userRepo;

	@Override
	public boolean isValid ( Long value, ConstraintValidatorContext context ) {
		// 1º about create, 2º about update
		return Objects.isNull( value )
				|| value == 0L || this.userRepo.findById( value ).isPresent();
	}
}
