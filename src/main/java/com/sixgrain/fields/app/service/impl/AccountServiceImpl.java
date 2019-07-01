package com.sixgrain.fields.app.service.impl;

import com.sixgrain.fields.app.dao.AccountRepository;
import com.sixgrain.fields.app.domain.Account;
import com.sixgrain.fields.app.service.AccountService;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Log
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    public Optional<Account> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Account> getByAccountName(String accountName) {
        return repository.findByAccountName(accountName);
    }

    @Override
    public List<Account> getByAccountEmail(String accountEmail) {
        return repository.findByAccountEmail(accountEmail);
    }

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public long insert(String accountName, String accountEmail) {
        Account accountDb = repository.save(new Account(accountName, accountEmail));
        return Objects.nonNull(accountDb) ? accountDb.getAccountId() : 0L;
    }

    @Override
    public Account insert(Account account) {
        return repository.save(account);
    }

}
