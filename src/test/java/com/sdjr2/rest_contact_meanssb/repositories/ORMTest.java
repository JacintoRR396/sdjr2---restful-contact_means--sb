package com.sdjr2.rest_contact_meanssb.repositories;

import Utils.JavaBeanTester;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.beans.IntrospectionException;

@SpringBootTest
class ORMTest {

	@Test
	void addressEntityTest () throws
														IntrospectionException {
		JavaBeanTester.test( AddressEntity.class, "com.sdjr2.rest_contact_meanssb" );
	}

}
