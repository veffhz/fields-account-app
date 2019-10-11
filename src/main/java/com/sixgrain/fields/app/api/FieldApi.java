package com.sixgrain.fields.app.api;

import com.fasterxml.jackson.annotation.JsonView;

import com.sixgrain.fields.app.domain.Views;
import com.sixgrain.fields.app.domain.Field;
import com.sixgrain.fields.app.exception.FieldNotFoundException;
import com.sixgrain.fields.app.service.FieldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class FieldApi {

    private final FieldService fieldService;

    @Autowired
    public FieldApi(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping("/api/field")
    @JsonView(Views.FieldFull.class)
    public List<Field> getAll() {
        log.info("get all fields");
        return fieldService.getAll();
    }

    @GetMapping("/api/field/{fieldId}")
    @JsonView(Views.FieldFull.class)
    public Field getById(@PathVariable Long fieldId) {
        log.info("get field by id {}", fieldId);
        return fieldService.getById(fieldId)
                .orElseThrow(FieldNotFoundException::new);
    }

    @PostMapping("/api/field/{fieldId}")
    public ResponseEntity update(@PathVariable Long fieldId, @Valid @RequestBody Field field) {
        log.info("update field {}", field);
        fieldService.update(fieldId, field);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/field")
    @JsonView(Views.Ids.class)
    public ResponseEntity<Field> create(@Valid @RequestBody Field field) {
        log.info("create field {} by id {}", field, field.getFieldId());
        Field savedField = fieldService.insert(field);
        return new ResponseEntity<>(savedField, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/field/{fieldId}")
    public ResponseEntity delete(@PathVariable Long fieldId) {
        Field field = fieldService.getById(fieldId)
                .orElseThrow(FieldNotFoundException::new);
        log.info("delete field by id {}", fieldId);
        fieldService.delete(field);
        return ResponseEntity.noContent().build();
    }

}
