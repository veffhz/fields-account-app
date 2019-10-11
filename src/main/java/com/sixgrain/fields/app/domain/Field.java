package com.sixgrain.fields.app.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "fields")
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "FieldId")
@NamedEntityGraph(name = "field.account", attributeNodes = @NamedAttributeNode("account"))
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.FieldId.class)
    @Column(name = "id")
    private Long fieldId;
    @Column
    @JsonView(Views.FieldLatLonName.class)
    private Double lat;
    @Column
    @JsonView(Views.FieldLatLonName.class)
    private Double lon;
    @Column
    @JsonView(Views.FieldLatLonName.class)
    private String fieldName;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonView({Views.AccountNameEmail.class, Views.AccountId.class})
    @JsonIgnoreProperties("Fields")
    @JsonUnwrapped
    private Account account;

    @CreatedDate
    @JsonIgnore
    @Column
    private LocalDateTime createdDate;

    public Field(Double lat, Double lon, String fieldName, Account account) {
        this.lat = lat;
        this.lon = lon;
        this.fieldName = fieldName;
        this.account = account;
    }
}
