package com.sdjr2.rest_contact_meanssb.repositories;

import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.entities.AuditableEntity;
import com.sdjr2.rest_contact_meanssb.models.enums.search.AddressFilterFieldEnum;
import com.sdjr2.rest_contact_meanssb.models.enums.search.OperatorFilterEnum;
import com.sdjr2.rest_contact_meanssb.repositories.filters.AddressSpecifications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressJpaRepositoryTest {

	private Integer numElements = 0;

	@Autowired
	AddressJpaRepository addressJpaRepository;

	@BeforeEach
	void setUp () {
		List<AddressEntity> entitiesDB = this.addressJpaRepository.findAll();
		this.numElements = entitiesDB.size();
	}

	@Test
	void findAllTest () {
		List<AddressEntity> entitiesRes = this.addressJpaRepository.findAll();

		assertNotNull( entitiesRes );
		assertFalse( entitiesRes.isEmpty() );
		assertEquals( this.numElements, entitiesRes.size() );
	}

	@Test
	void findAllWithPageRequestTest () {
		int offset = 0;
		int limit = 3;
		Page<AddressEntity> pageEntitiesRes = this.addressJpaRepository.findAll( PageRequest.of( offset, limit ) );

		this.assertGenericPage( offset, limit, pageEntitiesRes );
		assertEquals( limit, pageEntitiesRes.getNumberOfElements() );
		assertEquals( this.numElements / limit, pageEntitiesRes.getTotalPages() );
		assertEquals( this.numElements, ( int ) pageEntitiesRes.getTotalElements() );
	}

	private void assertGenericPage ( Integer offset, Integer limit, Page<AddressEntity> pageEntitiesRes ) {
		assertNotNull( pageEntitiesRes );
		assertFalse( pageEntitiesRes.isEmpty() );
		assertEquals( offset, pageEntitiesRes.getPageable().getPageNumber() );
		assertEquals( limit, pageEntitiesRes.getPageable().getPageSize() );
	}

	@Test
	void findAllWithPageRequestAndSortTest () {
		int offset = 0;
		int limit = 3;
		Page<AddressEntity> pageEntitiesRes = this.addressJpaRepository.findAll(
				PageRequest.of( offset, limit, Sort.by( AddressEntity.ATTR_STREET ) ) );

		this.assertGenericPage( offset, limit, pageEntitiesRes );
		assertEquals( limit, pageEntitiesRes.getNumberOfElements() );
		assertEquals( this.numElements / limit, pageEntitiesRes.getTotalPages() );
		assertEquals( this.numElements, ( int ) pageEntitiesRes.getTotalElements() );
		assertEquals( "Ancha", pageEntitiesRes.getContent().get( 0 ).getStreet() );
	}

	@Test
	void findAllWithPageRequestAndSortAndSpecificationTest () {
		int offset = 0;
		int limit = 3;
		Specification<AddressEntity> specification = Specification
				.where( AddressSpecifications.hasValuesStr(
						AddressFilterFieldEnum.STREET.getFieldMySQL(),
						OperatorFilterEnum.SW,
						List.of( "Avda" ) ) );
		Page<AddressEntity> pageEntitiesRes = this.addressJpaRepository.findAll( specification,
				PageRequest.of( offset, limit, Sort.by( AddressEntity.ATTR_STREET ) ) );

		this.assertGenericPage( offset, limit, pageEntitiesRes );
		assertEquals( 2, pageEntitiesRes.getNumberOfElements() );
		assertEquals( 1, pageEntitiesRes.getTotalPages() );
		assertEquals( 2, ( int ) pageEntitiesRes.getTotalElements() );
	}

	@Test
	void findByIdTest () {
		Optional<AddressEntity> entityOptRes = this.addressJpaRepository.findById( 1L );

		assertTrue( entityOptRes.isPresent() );
		assertEquals( "Avda Piedra el Gallo", entityOptRes.orElseThrow().getStreet() );
	}

	@Test
	void findByIdNotSuchElementExTest () {
		Optional<AddressEntity> entityOptRes = this.addressJpaRepository.findById( ( long ) this.numElements + 1 );

		assertTrue( entityOptRes.isEmpty() );
		assertThrows( NoSuchElementException.class, entityOptRes::orElseThrow );
	}

	@Test
	void findByStreetAndNumberAndPostalCodeTest () {
		String street = "Corredera";
		Optional<AddressEntity> entityOptRes = this.addressJpaRepository.findByStreetAndNumberAndPostalCode(
				street, "155", "41520" );

		assertTrue( entityOptRes.isPresent() );
		assertEquals( street, entityOptRes.orElseThrow().getStreet() );
	}

	@Test
	void saveForCreateTest () {
		String street = "Rosario";
		AuditableEntity auditEntity = new AuditableEntity( LocalDateTime.now(), "test",
				LocalDateTime.now(), "test" );
		AddressEntity entity = new AddressEntity( 0L, street, "23", "A", "El Viso del Alcor", "Sevilla",
				"España", "41520", "-5.7199300", "37.391060", "", auditEntity );

		AddressEntity entityRes = this.addressJpaRepository.save( entity );

		assertNotNull( entityRes );
		assertEquals( this.numElements + 1, entityRes.getId() );
		assertEquals( street, entityRes.getStreet() );
	}

	@Test
	void saveForUpdateTest () {
		Long id = 1L;
		String street = "Cervantes";
		AddressEntity entityDB = this.addressJpaRepository.findById( id ).orElseThrow();
		entityDB.setStreet( street );

		AddressEntity entityRes = this.addressJpaRepository.save( entityDB );

		assertNotNull( entityRes );
		assertEquals( id, entityRes.getId() );
		assertEquals( street, entityRes.getStreet() );
	}

	@Test
	void deleteByIdTest () {
		Long id = 1L;

		this.addressJpaRepository.deleteById( id );

		Optional<AddressEntity> entityOptRes = this.addressJpaRepository.findById( id );
		assertTrue( entityOptRes.isEmpty() );
		assertThrows( NoSuchElementException.class, entityOptRes::orElseThrow );
	}

	@Test
	void deleteTest () {
		Long id = 1L;
		AddressEntity entityDB = this.addressJpaRepository.findById( id ).orElseThrow();

		this.addressJpaRepository.delete( entityDB );

		Optional<AddressEntity> entityOptRes = this.addressJpaRepository.findById( id );
		assertTrue( entityOptRes.isEmpty() );
		assertThrows( NoSuchElementException.class, entityOptRes::orElseThrow );
	}
}
