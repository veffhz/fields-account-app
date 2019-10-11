package com.sixgrain.fields.app.dao;

import com.sixgrain.fields.app.domain.Account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @EntityGraph("account.fields")
    Page<Account> findAll(Pageable var1);
    @EntityGraph("account.fields")
    List<Account> findAll();
    Optional<Account> findByAccountName(String accountName);
    List<Account> findByAccountEmail(String accountEmail);
}
