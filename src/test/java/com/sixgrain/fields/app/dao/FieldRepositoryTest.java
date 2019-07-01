package com.sixgrain.fields.app.dao;

import com.sixgrain.fields.app.domain.Account;
import com.sixgrain.fields.app.domain.Field;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for FieldRepository")
@DataJpaTest
@ComponentScan
class FieldRepositoryTest {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("Test insert new field")
    void shouldInsertNewField() {
        Account account = accountRepository.findById(23424L).get();
        fieldRepository.save(new Field(11.22, 22.11, "Test", account));
        assertEquals(fieldRepository.count(), 2);
    }

    @Test
    @DisplayName("Test get field by id")
    void shouldGetAccountById() {
        Field field = fieldRepository.findById(133312L).get();
        assertEquals(field.getFieldName(), "My field number 1");
    }

    @Test
    @DisplayName("Test get field by name")
    void shouldGetAccountByName() {
        List<Field> fields = fieldRepository.findByFieldName("My field number 1");
        assertEquals(fields.get(0).getFieldId(), new Long(133312L));
    }

    @Test
    @DisplayName("Test delete field by id")
    void shouldDeleteAccountById() {
        fieldRepository.deleteById(133312L);
        assertEquals(fieldRepository.count(), 0);
    }

}