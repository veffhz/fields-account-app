package com.sixgrain.fields.app.dao;

import com.sixgrain.fields.app.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAll();
    Optional<Account> findByAccountName(String accountName);
    List<Account> findByAccountEmail(String accountEmail);
}
