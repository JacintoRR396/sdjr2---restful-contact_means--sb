package com.sdjr2.rest_contact_meanssb.controllers;

import Utils.DataMethods;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import org.junit.jupiter.api.*;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerRestTemplateTest {

	@Autowired
	TestRestTemplate client;

	@Spy
	List<AddressDTO> addressDTOs = new ArrayList<>();

	ObjectMapper objMapper;

	@BeforeEach
	public void setUp () {
		this.addressDTOs.add( DataMethods.getAddressDTO() );
		this.objMapper = new ObjectMapper();
	}

	@Order(1)
	@Test
	void getAddressesTest () {
		ResponseEntity<AddressDTO[]> resp = this.client.getForEntity( "/addresses", AddressDTO[].class );
		assertEquals( HttpStatus.OK, resp.getStatusCode() );
		assertEquals( MediaType.APPLICATION_JSON, resp.getHeaders().getContentType() );

		List<AddressDTO> dtosDB = Arrays.asList( resp.getBody() );
		assertNotNull( dtosDB );
		assertEquals( this.addressDTOs.get( 0 ).getId(), dtosDB.get( 1 ).getId() );
		assertEquals( this.addressDTOs.get( 0 ).getStreet(), dtosDB.get( 1 ).getStreet() );
	}

	@Order(2)
	@Test
	void getAddressByIdTest () {
		AddressDTO dto = this.addressDTOs.get( 0 );

		ResponseEntity<AddressDTO> resp = this.client.getForEntity( "/addresses/2", AddressDTO.class );
		assertEquals( HttpStatus.OK, resp.getStatusCode() );
		assertEquals( MediaType.APPLICATION_JSON, resp.getHeaders().getContentType() );

		AddressDTO dtoDB = resp.getBody();
		assertNotNull( dtoDB );
		assertEquals( dto.getId(), dtoDB.getId() );
		assertEquals( dto.getStreet(), dtoDB.getStreet() );
	}

	@Order(3)
	@Test
	void createAddressTest () throws
														JsonProcessingException {
		AddressDTO dto = this.addressDTOs.get( 0 );
		dto.setId( 0 );

		// Can also be done with AddressDTO.class
		ResponseEntity<String> resp = this.client.postForEntity( "/addresses", dto, String.class );
		assertEquals( HttpStatus.CREATED, resp.getStatusCode() );
		assertEquals( MediaType.APPLICATION_JSON, resp.getHeaders().getContentType() );

		String json = resp.getBody();
		assertNotNull( json );
		assertTrue( json.contains( dto.getStreet() ) );

		JsonNode jsonNode = this.objMapper.readTree( json );
		assertNotEquals( dto.getId(), jsonNode.path( "id" ).asInt() );
		assertEquals( dto.getStreet(), jsonNode.path( "street" ).asText() );
	}

	@Order(4)
	@Test
	void updateAddressTest () throws
														JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType( MediaType.APPLICATION_JSON );

		String street = "Rosario";
		// Since it is passed by reference, a clone is made by constructor so that the list object is not changed
		AddressDTO dto = AddressDTO.valueOf( this.addressDTOs.get( 0 ) );
		dto.setStreet( street );
		String requestBody = this.objMapper.writeValueAsString( dto );
		// The list object is assigned again since it has the initial state
		dto = AddressDTO.valueOf( this.addressDTOs.get( 0 ) );

		HttpEntity<String> httpEntityReq = new HttpEntity<>( requestBody, headers );

		ResponseEntity<AddressDTO> resp = this.client.exchange( "/addresses/2", HttpMethod.PUT, httpEntityReq,
				AddressDTO.class );
		assertEquals( HttpStatus.OK, resp.getStatusCode() );
		assertEquals( MediaType.APPLICATION_JSON, resp.getHeaders().getContentType() );

		AddressDTO dtoDB = resp.getBody();
		assertNotNull( dtoDB );
		assertEquals( dto.getId(), dtoDB.getId() );
		assertNotEquals( dto.getStreet(), dtoDB.getStreet() );
	}

	@Order(5)
	@Test
	void deleteAddressTest () {
		this.client.delete( "/addresses/4" );

		assertThrows( RestClientException.class, () -> {
			this.client.getForEntity( "/addresses/4", AddressDTO.class );
		} );
	}
}
