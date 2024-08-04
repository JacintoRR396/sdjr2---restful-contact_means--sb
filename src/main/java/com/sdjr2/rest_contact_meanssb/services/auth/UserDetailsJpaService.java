package com.sdjr2.rest_contact_meanssb.services.auth;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.entities.auth.UserEntity;
import com.sdjr2.rest_contact_meanssb.repositories.auth.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * {@link UserDetailsJpaService} class.
 * <p>
 * <strong>Service</strong> - Represents a class service about login users that implements to
 * {@link UserDetailsService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/08/03
 * @since 24/08/03
 */
@Service
@RequiredArgsConstructor
public class UserDetailsJpaService implements UserDetailsService {

	private final UserJpaRepository userRepo;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername ( String username ) throws
																														UsernameNotFoundException {
		Optional<UserEntity> entityDBOpt = this.userRepo.findByUsername( username );

		if ( entityDBOpt.isEmpty() ) {
			throw new CustomException( AppExceptionCodeEnum.STATUS_40402 );
		}

		UserEntity entityDB = entityDBOpt.orElseThrow();
		List<GrantedAuthority> authorities = entityDB.getRoles().stream()
				.map( role -> new SimpleGrantedAuthority( role.getName() ) )
				.collect( Collectors.toList() );

		return new User( entityDB.getUsername(), entityDB.getPwd(), entityDB.getEnabled(), true, true, true, authorities );
	}
}
