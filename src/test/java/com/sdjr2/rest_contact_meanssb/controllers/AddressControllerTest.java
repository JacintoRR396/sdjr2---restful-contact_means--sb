package com.sdjr2.rest_contact_meanssb.controllers;

import Utils.DataMethods;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.mappers.RespEntityErrorMapper;
import com.sdjr2.rest_contact_meanssb.services.impl.AddressServiceImpl;
import com.sdjr2.rest_contact_meanssb.utils.UDateTimeService;
import com.sdjr2.sb.library_commons.models.dto.BaseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(AddressController.class)
class AddressControllerTest {

	@MockBean
	UDateTimeService uDateTimeService;

	@MockBean
	RespEntityErrorMapper respEntityErrorMapper;

	@MockBean
	AddressServiceImpl addressService;

	@Autowired
	MockMvc mvc;

	@Spy
	List<AddressDTO> addressDTOs = new ArrayList<>();

	ObjectMapper objMapper;

	@BeforeEach
	public void setUp () {
		this.addressDTOs.add( DataMethods.getAddressDTO() );
		this.objMapper = new ObjectMapper();
	}

	@Test
	void getAddressesTest () throws
													 Exception {
		String strDTOs = this.objMapper.writeValueAsString( this.addressDTOs );
		when( this.addressService.getAll() ).thenReturn( this.addressDTOs );

		this.mvc.perform( MockMvcRequestBuilders.get( "/addresses" ).contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( MockMvcResultMatchers.content().json( strDTOs ) )
				.andExpect( MockMvcResultMatchers.status().isOk() )
				.andExpect( MockMvcResultMatchers.jsonPath( "$[0].id" ).value( 2 ) );

		verify( this.addressService, only() ).getAll();
	}

	@Test
	void getAddressByIdTest () throws
														 Exception {
		AddressDTO dto = this.addressDTOs.get( 0 );
		String strDTO = BaseDTO.toJsonStr( dto );
		when( this.addressService.getOneById( anyLong() ) ).thenReturn( dto );

		this.mvc.perform( MockMvcRequestBuilders.get( "/addresses/2" )
						.contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( MockMvcResultMatchers.content().json( strDTO ) )
				.andExpect( MockMvcResultMatchers.status().isOk() )
				.andExpect( MockMvcResultMatchers.jsonPath( "$.id" ).value( 2 ) );

		verify( this.addressService, only() ).getOneById( anyLong() );
	}

	@Test
	void createAddressTest () throws
														Exception {
		AddressDTO dto = this.addressDTOs.get( 0 );
		String strDTO = BaseDTO.toJsonStr( dto );
		when( this.addressService.create( any( AddressDTO.class ) ) ).thenReturn( dto );

		this.mvc.perform( MockMvcRequestBuilders.post( "/addresses" )
						.contentType( MediaType.APPLICATION_JSON ).content( strDTO ) )
				.andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( MockMvcResultMatchers.content().json( strDTO ) )
				.andExpect( MockMvcResultMatchers.status().isCreated() );

		verify( this.addressService, only() ).create( dto );
	}
}
