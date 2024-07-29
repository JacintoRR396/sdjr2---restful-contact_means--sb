package com.sdjr2.rest_contact_meanssb.services.impl;

import Utils.DataMethods;
import Utils.UtilMethods;
import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.FilterDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.OrderDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.SearchBodyDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.mappers.AddressMapper;
import com.sdjr2.rest_contact_meanssb.repositories.AddressJpaRepository;
import com.sdjr2.rest_contact_meanssb.services.AddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	List<AddressEntity> addressEntities = new ArrayList<>();

	@Spy
	List<AddressDTO> addressDTOs = new ArrayList<>();

	@BeforeEach
	public void setUp () {
		this.addressEntities.add( mock( AddressEntity.class ) );
		this.addressDTOs.add( mock( AddressDTO.class ) );
	}

	@Test
	void getAllTest () {
		when( this.addressRepo.findAll() ).thenReturn( this.addressEntities );
		when( this.addressMapper.toDTOs( this.addressEntities ) ).thenReturn( this.addressDTOs );

		List<AddressDTO> res = this.addressService.getAll();
		assertNotNull( res );
		assertFalse( res.isEmpty() );

		verify( this.addressRepo, only() ).findAll();
		verify( this.addressMapper, only() ).toDTOs( this.addressEntities );
	}

	@Test
	void getAllWhenIsEmptyTest () {
		when( this.addressRepo.findAll() ).thenReturn( new ArrayList<>() );
		when( this.addressMapper.toDTOs( anyList() ) ).thenReturn( new ArrayList<>() );

		List<AddressDTO> res = this.addressService.getAll();
		assertNotNull( res );
		assertTrue( res.isEmpty() );

		verify( this.addressRepo, only() ).findAll();
		verify( this.addressMapper, only() ).toDTOs( anyList() );
	}

	@Test
	void getAllWithPaginationTest () {
		int offset = 0;
		int limit = 3;
		Pageable pageRequest = DataMethods.getPageable( offset, limit, null );
		Page<AddressEntity> pageEntities = new PageImpl<>(
				this.addressEntities, pageRequest, this.addressEntities.size() );

		when( this.addressRepo.findAll( pageRequest ) ).thenReturn( pageEntities );
		when( this.addressMapper.toDTOs( pageEntities.getContent() ) ).thenReturn( this.addressDTOs );

		Page<AddressDTO> pageDtosRes = this.addressService.getAllWithPagination( offset, limit );
		UtilMethods.assertGenericPage( offset, limit, pageDtosRes );
		assertEquals( 1, pageDtosRes.getNumberOfElements() );
		assertEquals( 1, pageDtosRes.getTotalPages() );
		assertEquals( 1, ( int ) pageDtosRes.getTotalElements() );

		verify( this.addressRepo, only() ).findAll( pageRequest );
		verify( this.addressMapper, only() ).toDTOs( pageEntities.getContent() );
	}

	@Test
	void getAllWithSearchWithoutSpecificationTest () {
		SearchBodyDTO searchBodyDTO = DataMethods.getSearchBodyDTO( 0, 3, null, null );

		this.getAllWithSearchTest( searchBodyDTO, false );
	}

	@Test
	@Disabled
	void getAllWithSearchWithSpecificationTest () {
		List<FilterDTO> filtersDTO = DataMethods.getFiltersDTO();
		SearchBodyDTO searchBodyDTO = DataMethods.getSearchBodyDTO( 0, 3, null, filtersDTO );

		this.getAllWithSearchTest( searchBodyDTO, true );
	}

	private void getAllWithSearchTest ( SearchBodyDTO searchBodyDTO, boolean isWithSpec ) {
		Pageable pageRequest = DataMethods.getPageable( searchBodyDTO.getOffset(), searchBodyDTO.getLimit(), null );
		Specification<AddressEntity> specification = DataMethods.getSpecification();
		Page<AddressEntity> pageEntities = new PageImpl<>(
				this.addressEntities, pageRequest, this.addressEntities.size() );

		if ( isWithSpec ) {
			when( this.addressRepo.findAll( specification, pageRequest ) ).thenReturn( pageEntities );
		} else {
			when( this.addressRepo.findAll( pageRequest ) ).thenReturn( pageEntities );
		}
		when( this.addressMapper.toDTOs( pageEntities.getContent() ) ).thenReturn( this.addressDTOs );

		Page<AddressDTO> pageDtosRes = this.addressService.getAllWithSearch( searchBodyDTO );
		UtilMethods.assertGenericPage( searchBodyDTO.getOffset(), searchBodyDTO.getLimit(), pageDtosRes );
		assertEquals( 1, pageDtosRes.getNumberOfElements() );
		assertEquals( 1, pageDtosRes.getTotalPages() );
		assertEquals( 1, ( int ) pageDtosRes.getTotalElements() );

		if ( isWithSpec ) {
			verify( this.addressRepo, only() ).findAll( specification, pageRequest );
		} else {
			verify( this.addressRepo, only() ).findAll( pageRequest );
		}
		verify( this.addressMapper, only() ).toDTOs( pageEntities.getContent() );
	}

	@Test
	void createPageRequestWithPaginationAndSortWithSortsNull () {
		SearchBodyDTO searchBodyDTO = DataMethods.getSearchBodyDTO( 0, 3, null, null );

		this.createPageRequestWithPaginationAndSortTest( searchBodyDTO, false );
	}

	@Test
	void createPageRequestWithPaginationAndSortWithSortsEmpty () {
		SearchBodyDTO searchBodyDTO = DataMethods.getSearchBodyDTO( 0, 3, new ArrayList<>(), null );

		this.createPageRequestWithPaginationAndSortTest( searchBodyDTO, false );
	}

	@Test
	void createPageRequestWithPaginationAndSortWithSorts () {
		List<OrderDTO> ordersDTO = DataMethods.getOrdersDTO();
		SearchBodyDTO searchBodyDTO = DataMethods.getSearchBodyDTO( 0, 3, ordersDTO, null );

		this.createPageRequestWithPaginationAndSortTest( searchBodyDTO, false );
	}

	@Test
	void createPageRequestWithPaginationAndSortWithSortsEx () {
		List<OrderDTO> ordersDTO = DataMethods.getOrdersDTO();
		ordersDTO.get( 0 ).setField( "JR2" );
		SearchBodyDTO searchBodyDTO = DataMethods.getSearchBodyDTO( 0, 3, ordersDTO, null );

		this.createPageRequestWithPaginationAndSortTest( searchBodyDTO, true );
	}

	private void createPageRequestWithPaginationAndSortTest ( SearchBodyDTO searchBodyDTO, boolean isWithException ) {
		Method method;
		try {
			method = AddressServiceImpl.class.getDeclaredMethod(
					"createPageRequestWithPaginationAndSort", SearchBodyDTO.class );
		} catch ( NoSuchMethodException e ) {
			throw new RuntimeException( e );
		}
		method.setAccessible( true );

		Pageable res;
		if ( !isWithException ) {
			try {
				res = ( Pageable ) method.invoke( this.addressService, searchBodyDTO );
			} catch ( IllegalAccessException | InvocationTargetException e ) {
				throw new RuntimeException( e );
			}

			assertNotNull( res );
			assertEquals( searchBodyDTO.getOffset(), res.getPageNumber() );
			assertEquals( searchBodyDTO.getLimit(), res.getPageSize() );
		} else {
			Assertions.assertThrows(
					InvocationTargetException.class, () -> method.invoke( this.addressService, searchBodyDTO ) );
		}
	}

	@Test
	void createSpecificationAboutFiltersWithFiltersNull () {
		SearchBodyDTO searchBodyDTO = DataMethods.getSearchBodyDTO( 0, 3, null, null );

		this.createSpecificationAboutFiltersTest( searchBodyDTO, false );
	}

	@Test
	void createSpecificationAboutFiltersWithFiltersEmpty () {
		SearchBodyDTO searchBodyDTO = DataMethods.getSearchBodyDTO( 0, 3, null, new ArrayList<>() );

		this.createSpecificationAboutFiltersTest( searchBodyDTO, false );
	}

	@Test
	void createSpecificationAboutFiltersWithFilters () {
		List<FilterDTO> filtersDTO = DataMethods.getFiltersDTO();
		SearchBodyDTO searchBodyDTO = DataMethods.getSearchBodyDTO( 0, 3, null, filtersDTO );

		this.createSpecificationAboutFiltersTest( searchBodyDTO, true );
	}

	private void createSpecificationAboutFiltersTest ( SearchBodyDTO searchBodyDTO, boolean isCreated ) {
		Method method;
		try {
			method = AddressServiceImpl.class.getDeclaredMethod(
					"createSpecificationAboutFilters", SearchBodyDTO.class );
		} catch ( NoSuchMethodException e ) {
			throw new RuntimeException( e );
		}
		method.setAccessible( true );

		Specification<AddressEntity> res;
		try {
			res = ( Specification ) method.invoke( this.addressService, searchBodyDTO );
		} catch ( IllegalAccessException | InvocationTargetException e ) {
			throw new RuntimeException( e );
		}

		if ( !isCreated ) {
			assertNull( res );
		} else {
			assertNotNull( res );
		}
	}

	@Test
	void getOneByIdWhenExists () {
		AddressEntity entity = DataMethods.getAddressEntity();
		Optional<AddressEntity> entityOpt = Optional.of( entity );
		AddressDTO dto = DataMethods.getAddressDTO();

		when( this.addressRepo.findById( anyLong() ) ).thenReturn( entityOpt );
		when( this.addressMapper.toDTO( entity ) ).thenReturn( dto );

		AddressDTO res = this.addressService.getOneById( 1L );
		assertNotNull( res );

		verify( this.addressRepo, only() ).findById( anyLong() );
		verify( this.addressMapper, only() ).toDTO( any( AddressEntity.class ) );
	}
}
