package com.sixgrain.fields.app.service.impl;

import com.sixgrain.fields.app.dao.AccountRepository;
import com.sixgrain.fields.app.dao.FieldRepository;
import com.sixgrain.fields.app.domain.Account;
import com.sixgrain.fields.app.domain.Field;
import com.sixgrain.fields.app.exception.AccountNotFoundException;
import com.sixgrain.fields.app.exception.FieldNotFoundException;
import com.sixgrain.fields.app.service.FieldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository,
                            AccountRepository accountRepository) {
        this.fieldRepository = fieldRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Field> getById(long id) {
        return fieldRepository.findById(id);
    }

    @Override
    public List<Field> getByFieldName(String fieldName) {
        return fieldRepository.findByFieldName(fieldName);
    }

    @Override
    public List<Field> getAll() {
        return fieldRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        fieldRepository.deleteById(id);
    }

    @Override
    public void delete(Field field) {
        fieldRepository.delete(field);
    }

    @Override
    public Field update(Long fieldId, Field field) {
        Account accountDb = accountRepository.findByAccountName(field.getAccount().getAccountName())
                .orElseThrow(AccountNotFoundException::new);

        Field fieldDb = fieldRepository.findById(fieldId).orElseThrow(FieldNotFoundException::new);

        fieldDb.setLon(field.getLon());
        fieldDb.setLat(field.getLat());
        fieldDb.setFieldName(field.getFieldName());

        fieldDb.setAccount(accountDb);
        return fieldRepository.save(fieldDb);
    }

    @Override
    public long insert(Double lat, Double lon, String fieldName, Account account) {
        Field fieldDb = fieldRepository.save(new Field(lat, lon, fieldName, account));
        return Objects.nonNull(fieldDb) ? fieldDb.getFieldId() : 0L;
    }

    @Override
    public Field insert(Field field) {
        Account accountDb = accountRepository.findByAccountName(field.getAccount().getAccountName())
                .orElseThrow(AccountNotFoundException::new);
        field.setAccount(accountDb);
        return fieldRepository.save(field);
    }

}
