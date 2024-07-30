package com.sdjr2.rest_contact_meanssb.repositories;

import Utils.DataMethods;
import Utils.UtilMethods;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.enums.search.AddressSortFieldEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

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
		Page<AddressEntity> pageEntitiesRes = this.addressJpaRepository.findAll(
				DataMethods.getPageable( offset, limit, null ) );

		UtilMethods.assertGenericPage( offset, limit, pageEntitiesRes );
		assertEquals( limit, pageEntitiesRes.getNumberOfElements() );
		assertEquals( this.numElements / limit, pageEntitiesRes.getTotalPages() );
		assertEquals( this.numElements, ( int ) pageEntitiesRes.getTotalElements() );
	}

	@Test
	void findAllWithPageRequestAndSortTest () {
		int offset = 0;
		int limit = 3;
		Page<AddressEntity> pageEntitiesRes = this.addressJpaRepository.findAll(
				DataMethods.getPageable( offset, limit, AddressSortFieldEnum.CITY.getFieldMySQL() ) );

		UtilMethods.assertGenericPage( offset, limit, pageEntitiesRes );
		assertEquals( limit, pageEntitiesRes.getNumberOfElements() );
		assertEquals( this.numElements / limit, pageEntitiesRes.getTotalPages() );
		assertEquals( this.numElements, ( int ) pageEntitiesRes.getTotalElements() );
		assertEquals( "Ancha", pageEntitiesRes.getContent().get( 0 ).getStreet() );
	}

	@Test
	void findAllWithPageRequestAndSortAndSpecificationTest () {
		int offset = 0;
		int limit = 3;
		Page<AddressEntity> pageEntitiesRes = this.addressJpaRepository.findAll( DataMethods.getSpecification(),
				DataMethods.getPageable( offset, limit, AddressSortFieldEnum.CITY.getFieldMySQL() ) );

		UtilMethods.assertGenericPage( offset, limit, pageEntitiesRes );
		assertEquals( 3, pageEntitiesRes.getNumberOfElements() );
		assertEquals( 2, pageEntitiesRes.getTotalPages() );
		assertEquals( 6, ( int ) pageEntitiesRes.getTotalElements() );
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
	void findByStreetAndNumberAndLetterAndPostalCodeTest () {
		AddressEntity entityDB = DataMethods.getAddressEntity();
		Optional<AddressEntity> entityOptRes = this.addressJpaRepository.findByStreetAndNumberAndLetterAndPostalCode(
				entityDB.getStreet(), entityDB.getNumber(), entityDB.getLetter(), entityDB.getPostalCode() );

		assertTrue( entityOptRes.isPresent() );
		assertEquals( entityDB.getStreet(), entityOptRes.orElseThrow().getStreet() );
	}

	@Test
	void saveForCreateTest () {
		AddressEntity entityReq = DataMethods.getAddressEntity();
		entityReq.setId( 0L );

		AddressEntity entityRes = this.addressJpaRepository.save( entityReq );

		assertNotNull( entityRes );
		assertEquals( this.numElements + 1, entityRes.getId() );
		assertEquals( entityReq.getStreet(), entityRes.getStreet() );
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
