package com.sixgrain.fields.app.dao;

import com.sixgrain.fields.app.domain.Account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for AccountRepository")
@DataJpaTest
@ComponentScan
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @Test
    @DisplayName("Test insert new account")
    void shouldInsertNewAccount() {
        accountRepository.save(new Account("test", "test"));
        assertEquals(accountRepository.count(), 2);
    }

    @Test
    @DisplayName("Test get account by id")
    void shouldGetAccountById() {
        Account account = accountRepository.findById(23424L).get();
        assertEquals(account.getAccountName(), "UserId");
        assertEquals(account.getAccountEmail(), "user1@mail.com");
    }

    @Test
    @DisplayName("Test get account by name")
    void shouldGetAccountByName() {
        Account account = accountRepository.findByAccountName("UserId").get();
        assertEquals(account.getAccountId(), new Long(23424));
        assertEquals(account.getAccountEmail(), "user1@mail.com");
    }

    @Test
    @DisplayName("Test delete account by id")
    void shouldDeleteAccountById() {
        fieldRepository.deleteById(133312L);
        accountRepository.deleteById(23424L);
        assertEquals(accountRepository.count(), 0);
    }

}