package com.sdjr2.rest_contact_meanssb.services.impl;

import Utils.DataMethods;
import Utils.UtilMethods;
import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.enums.search.AddressSortFieldEnum;
import com.sdjr2.rest_contact_meanssb.models.mappers.AddressMapper;
import com.sdjr2.rest_contact_meanssb.repositories.AddressJpaRepository;
import com.sdjr2.rest_contact_meanssb.repositories.filters.AddressSpecifications;
import com.sdjr2.rest_contact_meanssb.services.AddressService;
import com.sdjr2.sb.library_commons.exceptions.CustomException;
import com.sdjr2.sb.library_commons.models.dto.search.FilterDTO;
import com.sdjr2.sb.library_commons.models.dto.search.SearchBodyDTO;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringBootTest
class AddressServiceImplTest {

	@MockBean
	AddressMapper addressMapper;

	@MockBean
	AddressSpecifications addressSpecs;

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
		this.addressEntities.add( DataMethods.getAddressEntity() );
		this.addressDTOs.add( DataMethods.getAddressDTO() );
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

		this.getAllWithSearchCommon( searchBodyDTO, false );
	}

	@Test
	void getAllWithSearchWithSpecificationTest () {
		List<FilterDTO> filtersDTO = DataMethods.getFiltersDTO();
		SearchBodyDTO searchBodyDTO = DataMethods.getSearchBodyDTO( 0, 3, null, filtersDTO );

		this.getAllWithSearchCommon( searchBodyDTO, true );
	}

	private void getAllWithSearchCommon ( SearchBodyDTO searchBodyDTO, boolean isWithSpec ) {
		Pageable pageRequest = DataMethods.getPageable( searchBodyDTO.getOffset(), searchBodyDTO.getLimit(), null );
		Page<AddressEntity> pageEntities = new PageImpl<>(
				this.addressEntities, pageRequest, this.addressEntities.size() );

		if ( isWithSpec ) {
			when( this.addressRepo.findAll( any( Specification.class ), any( Pageable.class ) ) ).thenReturn( pageEntities );
		} else {
			when( this.addressRepo.findAll( any( Pageable.class ) ) ).thenReturn( pageEntities );
		}
		when( this.addressMapper.toDTOs( pageEntities.getContent() ) ).thenReturn( this.addressDTOs );

		Page<AddressDTO> pageDtosRes = this.addressService.getAllWithSearch( searchBodyDTO );
		UtilMethods.assertGenericPage( searchBodyDTO.getOffset(), searchBodyDTO.getLimit(), pageDtosRes );
		assertEquals( 1, pageDtosRes.getNumberOfElements() );
		assertEquals( 1, pageDtosRes.getTotalPages() );
		assertEquals( 1, ( int ) pageDtosRes.getTotalElements() );

		if ( isWithSpec ) {
			verify( this.addressRepo, only() ).findAll( any( Specification.class ), any( Pageable.class ) );
		} else {
			verify( this.addressRepo, only() ).findAll( any( Pageable.class ) );
		}
		verify( this.addressMapper, only() ).toDTOs( pageEntities.getContent() );
	}

	@Test
	void createSortOrderTest () throws
															InvocationTargetException,
															IllegalAccessException,
															NoSuchMethodException {
		Method method = AddressServiceImpl.class.getDeclaredMethod(
				"createSortOrder", String.class, Sort.Direction.class );
		method.setAccessible( true );

		Sort.Order res = ( Sort.Order ) method.invoke(
				this.addressService, AddressSortFieldEnum.CITY.name(), Sort.Direction.ASC );

		assertNotNull( res );
	}

	@Test
	void createSpecificationAboutFiltersWithFiltersNullTest () {
		SearchBodyDTO searchBodyDTO = DataMethods.getSearchBodyDTO( 0, 3, null, null );

		this.createSpecificationAboutFiltersCommon( searchBodyDTO, false );
	}

	@Test
	void createSpecificationAboutFiltersWithFiltersEmptyTest () {
		SearchBodyDTO searchBodyDTO = DataMethods.getSearchBodyDTO( 0, 3, null, new ArrayList<>() );

		this.createSpecificationAboutFiltersCommon( searchBodyDTO, false );
	}

	@Test
	@Disabled("Not apply Specification mock")
	void createSpecificationAboutFiltersWithFiltersTest () {
		List<FilterDTO> filtersDTO = DataMethods.getFiltersDTO();
		SearchBodyDTO searchBodyDTO = DataMethods.getSearchBodyDTO( 0, 3, null, filtersDTO );

		this.createSpecificationAboutFiltersCommon( searchBodyDTO, true );
	}

	private void createSpecificationAboutFiltersCommon ( SearchBodyDTO searchBodyDTO, boolean isCreated ) {
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
	void getOneByIdWhenExistsTest () {
		AddressEntity entity = DataMethods.getAddressEntity();
		Optional<AddressEntity> entityOpt = Optional.of( entity );
		Long id = entity.getId();
		AddressDTO dto = DataMethods.getAddressDTO();

		when( this.addressRepo.findById( anyLong() ) ).thenReturn( entityOpt );
		when( this.addressMapper.toDTO( entity ) ).thenReturn( dto );

		AddressDTO res = this.addressService.getOneById( id );
		assertNotNull( res );
		assertEquals( id, res.getId() );

		verify( this.addressRepo, only() ).findById( anyLong() );
		verify( this.addressMapper, only() ).toDTO( any( AddressEntity.class ) );
	}

	@Test
	void getOneByIdWithExceptionTest () {
		AddressEntity entity = DataMethods.getAddressEntity();
		Long id = entity.getId();

		when( this.addressRepo.findById( anyLong() ) ).thenThrow( NoSuchElementException.class );

		assertThrows( CustomException.class, () -> this.addressService.getOneById( id ) );

		verify( this.addressRepo, only() ).findById( anyLong() );
	}

	@Test
	void createWhenNotExistsTest () {
		AddressDTO dto = DataMethods.getAddressDTO();
		dto.setId( 0L );
		AddressEntity entity = DataMethods.getAddressEntity();

		when( this.addressRepo.findByStreetAndNumberAndLetterAndPostalCode( anyString(), anyString(), anyString(),
				anyString() ) ).thenReturn( Optional.empty() );
		when( this.addressMapper.toEntity( any( AddressDTO.class ), any(), any() ) ).thenReturn( entity );
		when( this.addressRepo.save( any( AddressEntity.class ) ) ).thenReturn( entity );
		when( this.addressMapper.toDTO( any( AddressEntity.class ) ) ).thenReturn( dto );

		AddressDTO res = this.addressService.create( dto );
		assertNotNull( res );
		assertEquals( dto.getId(), res.getId() );

		verify( this.addressRepo, times( 1 ) ).findByStreetAndNumberAndLetterAndPostalCode(
				anyString(), anyString(), anyString(), anyString() );
		verify( this.addressMapper, times( 1 ) ).toEntity( any( AddressDTO.class ), any(), any() );
		verify( this.addressRepo, times( 1 ) ).save( any( AddressEntity.class ) );
		verify( this.addressMapper, times( 1 ) ).toDTO( any( AddressEntity.class ) );
	}

	@Test
	void createWithExceptionTest () {
		AddressDTO dto = DataMethods.getAddressDTO();
		dto.setId( 0L );
		AddressEntity entity = DataMethods.getAddressEntity();

		when( this.addressRepo.findByStreetAndNumberAndLetterAndPostalCode( anyString(), anyString(), anyString(),
				anyString() ) ).thenReturn( Optional.of( entity ) );

		assertThrows( CustomException.class, () -> this.addressService.create( dto ) );

		verify( this.addressRepo, times( 1 ) ).findByStreetAndNumberAndLetterAndPostalCode(
				anyString(), anyString(), anyString(), anyString() );
	}

	@Test
	void updateWhenNotDuplicateAndExistsTest () {
		AddressDTO dto = DataMethods.getAddressDTO();
		Long id = dto.getId();
		AddressEntity entity = DataMethods.getAddressEntity();
		Optional<AddressEntity> entityOpt = Optional.of( entity );

		when( this.addressRepo.findByStreetAndNumberAndLetterAndPostalCode( anyString(), anyString(), anyString(),
				anyString() ) ).thenReturn( Optional.empty() );
		when( this.addressRepo.findById( anyLong() ) ).thenReturn( entityOpt );
		when( this.addressMapper.toEntity( any( AddressDTO.class ), anyString(), any( AddressEntity.class ) ) )
				.thenReturn( entity );
		when( this.addressRepo.save( any( AddressEntity.class ) ) ).thenReturn( entity );
		when( this.addressMapper.toDTO( any( AddressEntity.class ) ) ).thenReturn( dto );

		AddressDTO res = this.addressService.update( id, dto );
		assertNotNull( res );
		assertEquals( dto.getId(), res.getId() );

		verify( this.addressRepo, times( 1 ) ).findByStreetAndNumberAndLetterAndPostalCode(
				anyString(), anyString(), anyString(), anyString() );
		verify( this.addressRepo, times( 1 ) ).findById( anyLong() );
		verify( this.addressMapper, times( 1 ) ).toEntity( any( AddressDTO.class ),
				anyString(), any( AddressEntity.class ) );
		verify( this.addressRepo, times( 1 ) ).save( any( AddressEntity.class ) );
		verify( this.addressMapper, times( 1 ) ).toDTO( any( AddressEntity.class ) );
	}

	@Test
	void updateWithExceptionDuplicateTest () {
		AddressDTO dto = DataMethods.getAddressDTO();
		Long id = dto.getId();
		AddressEntity entity = DataMethods.getAddressEntity();

		when( this.addressRepo.findByStreetAndNumberAndLetterAndPostalCode( anyString(), anyString(), anyString(),
				anyString() ) ).thenReturn( Optional.of( entity ) );

		assertThrows( CustomException.class, () -> this.addressService.update( id, dto ) );

		verify( this.addressRepo, times( 1 ) ).findByStreetAndNumberAndLetterAndPostalCode(
				anyString(), anyString(), anyString(), anyString() );
	}

	@Test
	void updateWithExceptionNotExistsTest () {
		AddressDTO dto = DataMethods.getAddressDTO();
		Long id = dto.getId();

		when( this.addressRepo.findByStreetAndNumberAndLetterAndPostalCode( anyString(), anyString(), anyString(),
				anyString() ) ).thenReturn( Optional.empty() );
		when( this.addressRepo.findById( anyLong() ) ).thenThrow( NoSuchElementException.class );

		assertThrows( CustomException.class, () -> this.addressService.update( id, dto ) );

		verify( this.addressRepo, times( 1 ) ).findByStreetAndNumberAndLetterAndPostalCode(
				anyString(), anyString(), anyString(), anyString() );
		verify( this.addressRepo, times( 1 ) ).findById( anyLong() );
	}

	@Test
	void deleteWhenExistsTest () {
		AddressEntity entity = DataMethods.getAddressEntity();
		Optional<AddressEntity> entityOpt = Optional.of( entity );
		Long id = entity.getId();

		when( this.addressRepo.findById( anyLong() ) ).thenReturn( entityOpt );

		this.addressService.delete( id );

		verify( this.addressRepo, times( 1 ) ).findById( anyLong() );
	}

	@Test
	void deleteWithExceptionTest () {
		AddressEntity entity = DataMethods.getAddressEntity();
		Long id = entity.getId();

		when( this.addressRepo.findById( anyLong() ) ).thenThrow( NoSuchElementException.class );

		assertThrows( CustomException.class, () -> this.addressService.delete( id ) );

		verify( this.addressRepo, only() ).findById( anyLong() );
	}
}
