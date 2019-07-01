package com.sixgrain.fields.app.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(Views.AccountId.class)
    private Long accountId;
    @Column(unique = true)
    @JsonView(Views.AccountNameEmail.class)
    private String accountName;
    @Column
    @JsonView(Views.AccountNameEmail.class)
    private String accountEmail;

    @CreatedDate
    @JsonIgnore
    @Column
    private LocalDateTime createdDate;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="account")
    @JsonView(Views.FieldId.class)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Field> fields;

    public Account(String accountName, String accountEmail) {
        this.accountName = accountName;
        this.accountEmail = accountEmail;
    }
}
