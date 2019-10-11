package com.sixgrain.fields.app.api;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;

import com.sixgrain.fields.app.domain.Views;
import com.sixgrain.fields.app.domain.Account;
import com.sixgrain.fields.app.exception.AccountNotFoundException;
import com.sixgrain.fields.app.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AccountApi {

    private final AccountService accountService;

    @Autowired
    public AccountApi(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/api/account")
    @JsonView(Views.AccountFull.class)
    public List<Account> getAll() {
        log.info("get all accounts");
        return accountService.getAll();
    }

    @GetMapping("/api/account/{account_id}")
    @JsonView(Views.AccountFull.class)
    public Account getById(@PathVariable Long account_id) {
        log.info("get account by id {}", account_id);
        return accountService.getById(account_id)
                .orElseThrow(AccountNotFoundException::new);
    }

}
