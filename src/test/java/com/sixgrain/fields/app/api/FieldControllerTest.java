package com.sixgrain.fields.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixgrain.fields.app.domain.Account;
import com.sixgrain.fields.app.domain.Field;
import com.sixgrain.fields.app.service.FieldService;

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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Test for field controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FieldApi.class)
@Import(FieldApi.class)
class FieldControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private FieldService fieldService;

    @Test
    @DisplayName("Test get all fields page on /api/field")
    void shouldGetAllFields() throws Exception {
        List<Field> fields = Arrays.asList(
                new Field(11.22, 22.11, "Test", new Account()),
                new Field(11.22, 22.11, "Test", new Account()));

        given(this.fieldService.getAll()).willReturn(fields);

        String responseBody = "[{\"FieldId\":null,\"Lat\":11.22,\"Lon\":22.11,\"FieldName\":\"Test\",\"AccountName\":null," +
                "\"AccountEmail\":null},{\"FieldId\":null,\"Lat\":11.22,\"Lon\":22.11,\"FieldName\":\"Test\"," +
                "\"AccountName\":null,\"AccountEmail\":null}]";

        this.mvc.perform(get("/api/field")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));
    }

    @Test
    @DisplayName("Test get field on /api/field/{id}")
    void shouldGetField() throws Exception {
        Field field = new Field(11.22, 22.11, "Test", new Account());

        given(this.fieldService.getById(123L)).willReturn(Optional.of(field));

        String responseBody = "{\"FieldId\":null,\"Lat\":11.22,\"Lon\":22.11,\"FieldName\":\"Test\",\"AccountName\":null,\"AccountEmail\":null}";

        this.mvc.perform(get("/api/field/123")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));
    }

    @Test
    @DisplayName("Test update field on /api/field/{id}")
    void shouldUpdateField() throws Exception {
        Field field = new Field(11.22, 22.11, "Test", new Account());

        given(this.fieldService.update(123L, field)).willReturn(field);

        this.mvc.perform(post("/api/field/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(field))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(this.fieldService, times(1)).update(123L, field);
    }

    @Test
    @DisplayName("Test create field on post /api/field")
    void shouldCreateAuthor() throws Exception {
        Account account = new Account();
        Field field = new Field(11.22, 22.11, "Test", account);

        account.setAccountId(11L);
        field.setFieldId(22L);

        given(this.fieldService.insert(field)).willReturn(field);

        String responseBody = "{\"FieldId\":22,\"AccountId\":11}";

        this.mvc.perform(put("/api/field")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(field))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(responseBody));
        verify(this.fieldService, times(1)).insert(field);
    }

    @Test
    @DisplayName("Test delete field on /api/field/{id}")
    void shouldDeleteAuthorById() throws Exception {
        Field field = new Field(11.22, 22.11, "Test", new Account());
        given(this.fieldService.getById(123L)).willReturn(Optional.of(field));

        this.mvc.perform(delete("/api/field/123")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isNoContent());
        verify(this.fieldService, times(1)).delete(field);
    }

}