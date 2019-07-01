package com.sixgrain.fields.app.api;

import com.sixgrain.fields.app.controller.HomeController;
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

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Test for Home Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HomeController.class)
@Import(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FieldService fieldService;

    @Test
    @DisplayName("Test get page on / ")
    void shouldGetIndexPage() throws Exception {
        List<Field> fields = Arrays.asList(
                new Field(11.22, 22.11, "Test", new Account()),
                new Field(11.22, 22.11, "Test", new Account()));

        given(this.fieldService.getAll()).willReturn(fields);

        this.mvc.perform(get("/").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("fields", fields));
    }
}