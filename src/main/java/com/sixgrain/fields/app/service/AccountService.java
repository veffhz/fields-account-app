package com.sixgrain.fields.app.service;

import com.sixgrain.fields.app.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> getById(long id);
    Optional<Account> getByAccountName(String accountName);
    List<Account> getByAccountEmail(String accountEmail);
    List<Account> getAll();
    Account insert(Account account);
    void deleteById(long id);
    long insert(String accountName, String accountEmail);
}
