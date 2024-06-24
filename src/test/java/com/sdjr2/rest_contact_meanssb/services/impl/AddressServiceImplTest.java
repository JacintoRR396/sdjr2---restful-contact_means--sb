package com.sdjr2.rest_contact_meanssb.services.impl;

import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.mappers.AddressMapper;
import com.sdjr2.rest_contact_meanssb.repositories.AddressJpaRepository;
import com.sdjr2.rest_contact_meanssb.services.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringBootTest
class AddressServiceImplTest {

	@MockBean
	AddressMapper addressMapper;

	@MockBean
	AddressJpaRepository addressRepo;

	@Autowired
	@Qualifier("addressServiceImpl")
	AddressService addressService;

	@Spy
	List<AddressEntity> addressEntities;

	@Spy
	List<AddressDTO> addressDTOs;

	@BeforeEach
	public void setUp () {
		this.addressEntities.add( mock( AddressEntity.class ) );
		this.addressDTOs.add( mock( AddressDTO.class ) );
	}

	@Tag("Get Addresses")
	@Test
	void getAddressesWhenIsEmptyTest () {
		when( this.addressRepo.findAll() ).thenReturn( new ArrayList<>() );
		when( this.addressMapper.toDTOs( anyList() ) ).thenReturn( new ArrayList<>() );

		List<AddressDTO> res = this.addressService.getAddresses();
		assertNotNull( res );
		assertTrue( res.isEmpty() );

		verify( this.addressRepo, only() ).findAll();
		verify( this.addressMapper, only() ).toDTOs( anyList() );
	}

	@Tag("Get Addresses")
	@Test
	void getAddressesTest () {
		when( this.addressRepo.findAll() ).thenReturn( this.addressEntities );
		when( this.addressMapper.toDTOs( this.addressEntities ) ).thenReturn( this.addressDTOs );

		List<AddressDTO> res = this.addressService.getAddresses();
		assertNotNull( res );
		assertFalse( res.isEmpty() );

		verify( this.addressRepo, only() ).findAll();
		verify( this.addressMapper, only() ).toDTOs( this.addressEntities );
	}
}
