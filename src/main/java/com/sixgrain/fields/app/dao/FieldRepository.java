package com.sixgrain.fields.app.dao;

import com.sixgrain.fields.app.domain.Field;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {
    @EntityGraph("field.account")
    Page<Field> findAll(Pageable var1);
    @EntityGraph("field.account")
    List<Field> findAll();
    List<Field> findByFieldName(String fieldName);
}
