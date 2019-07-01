package com.sixgrain.fields.app.api;

import com.sixgrain.fields.app.domain.Account;
import com.sixgrain.fields.app.service.AccountService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Test for account controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountApi.class)
@Import(AccountApi.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @Test
    @DisplayName("Test get all accounts page on /api/account")
    void shouldGetAllAccounts() throws Exception {
        List<Account> accounts = Arrays.asList(
                new Account("test", "test"),
                new Account("test", "test"));

        given(this.accountService.getAll()).willReturn(accounts);

        String responseBody = "[{\"AccountId\":null,\"AccountName\":\"test\",\"AccountEmail\":\"test\",\"Fields\":null}," +
                "{\"AccountId\":null,\"AccountName\":\"test\",\"AccountEmail\":\"test\",\"Fields\":null}]";

        this.mvc.perform(get("/api/account")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));
    }

    @Test
    @DisplayName("Test get account on /api/account/{id}")
    void shouldGetAccount() throws Exception {
        Account account = new Account("test", "test");

        given(this.accountService.getById(123L)).willReturn(Optional.of(account));

        String responseBody = "{\"AccountId\":null,\"AccountName\":\"test\",\"AccountEmail\":\"test\",\"Fields\":null}";

        this.mvc.perform(get("/api/account/123")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));
    }

}