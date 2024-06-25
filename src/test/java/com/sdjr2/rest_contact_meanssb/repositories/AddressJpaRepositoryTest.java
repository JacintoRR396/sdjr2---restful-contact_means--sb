package com.sdjr2.rest_contact_meanssb.repositories;

import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.entities.AuditableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
  void setUp() {
    List<AddressEntity> entities = this.addressJpaRepository.findAll();
    this.numElements = entities.size();
  }

  @Tag("Get All")
  @Test
  void findAllTest() {
    List<AddressEntity> entitiesRes = this.addressJpaRepository.findAll();

    assertFalse(entitiesRes.isEmpty());
    assertEquals(4, entitiesRes.size());
  }

  @Tag("Get One")
  @Test
  void findByIdTest() {
    Optional<AddressEntity> entityOptRes = this.addressJpaRepository.findById(1);

    assertTrue(entityOptRes.isPresent());
    assertEquals("Avda Piedra el Gallo", entityOptRes.orElseThrow().getStreet());
  }

  @Tag("Get One")
  @Test
  void findByIdNotSuchElementExTest() {
    Optional<AddressEntity> entityOptRes = this.addressJpaRepository.findById(this.numElements + 1);

    assertTrue(entityOptRes.isEmpty());
    assertThrows(NoSuchElementException.class, entityOptRes::orElseThrow);
  }

  @Tag("Save One")
  @Test
  void saveTest() {
    String street = "Rosario";
    AuditableEntity auditEntity = new AuditableEntity(LocalDateTime.now(), "test", LocalDateTime.now(), "test");
    AddressEntity entity = new AddressEntity(0,street,"23","A","El Viso del Alcor","Sevilla","España","41520","-5.7199300","37.391060","", auditEntity);

    AddressEntity entityRes = this.addressJpaRepository.save(entity);

    assertNotNull(entityRes);
    assertEquals(this.numElements + 1, entityRes.getId());
    assertEquals(street, entityRes.getStreet());
  }

  @Tag("Update One")
  @Test
  void updateTest() {
    Integer id = 1;
    String street = "Cervantes";
    Optional<AddressEntity> entityOpt = this.addressJpaRepository.findById(id);
    AddressEntity entity = entityOpt.orElseThrow();
    entity.setStreet(street);

    AddressEntity entityRes = this.addressJpaRepository.save(entity);

    assertNotNull(entityRes);
    assertEquals(id, entityRes.getId());
    assertEquals(street, entityRes.getStreet());
  }

  @Tag("Delete One")
  @Test
  void deleteByIdTest() {
    Integer id = 1;

    this.addressJpaRepository.deleteById(id);

    Optional<AddressEntity> entityOptRes = this.addressJpaRepository.findById(id);
    assertTrue(entityOptRes.isEmpty());
    assertThrows(NoSuchElementException.class, entityOptRes::orElseThrow);
  }

  @Tag("Delete One")
  @Test
  void deleteTest() {
    Integer id = 1;
    Optional<AddressEntity> entityOpt = this.addressJpaRepository.findById(id);
    AddressEntity entity = entityOpt.orElseThrow();

    this.addressJpaRepository.delete(entity);

    Optional<AddressEntity> entityOptRes = this.addressJpaRepository.findById(id);
    assertTrue(entityOptRes.isEmpty());
    assertThrows(NoSuchElementException.class, entityOptRes::orElseThrow);
  }
}
