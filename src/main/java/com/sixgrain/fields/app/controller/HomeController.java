package com.sixgrain.fields.app.controller;

import lombok.extern.slf4j.Slf4j;

import com.sixgrain.fields.app.domain.Field;
import com.sixgrain.fields.app.service.FieldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
public class HomeController {

    private final FieldService fieldService;

    @Autowired
    public HomeController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "page", required = false, defaultValue = "") Integer page,
                        @RequestParam(value = "size", required = false, defaultValue = "") Integer size) {

        int currentPage = Objects.isNull(page) ? 1 : page;
        int pageSize = Objects.isNull(size) ? 15 : size;

        log.info("get / page {}, size {}", currentPage, pageSize);

        Page<Field> fieldPage = fieldService.getAllByPage(currentPage, pageSize);
        model.addAttribute("fieldPage", fieldPage);

        int totalPages = fieldPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "index";
    }

}
