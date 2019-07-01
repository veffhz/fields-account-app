package com.sixgrain.fields.app.controller;

import lombok.extern.slf4j.Slf4j;

import com.sixgrain.fields.app.domain.Field;
import com.sixgrain.fields.app.service.FieldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class HomeController {

    private final FieldService fieldService;

    @Autowired
    public HomeController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping("/")
    public String index(Model model) {
        log.info("get /");
        List<Field> fields = fieldService.getAll();
        model.addAttribute("fields", fields);
        return "index";
    }

}
