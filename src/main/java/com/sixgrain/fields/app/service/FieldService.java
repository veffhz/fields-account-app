package com.sixgrain.fields.app.service;

import com.sixgrain.fields.app.domain.Account;
import com.sixgrain.fields.app.domain.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface FieldService {
    Optional<Field> getById(long id);
    List<Field> getByFieldName(String fieldName);
    List<Field> getAll();
    Page<Field> getAllByPage(int currentPage, int pageSize);
    Field update(Long fieldId, Field field);
    void deleteById(long id);
    void delete(Field field);
    long insert(Double lat, Double lon, String fieldName, Account account);
    Field insert(Field field);
}
