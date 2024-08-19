package com.sdjr2.rest_contact_meanssb.models.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = AddressExistsByIdValidator.class)
public @interface AddressExistsById {
	String message () default "does not exist in the database";

	Class<?>[] groups () default {};

	Class<? extends Payload>[] payload () default {};
}
