package com.sixgrain.fields.app.dao;

import com.sixgrain.fields.app.domain.Field;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findAll();
    List<Field> findByFieldName(String fieldName);
}
