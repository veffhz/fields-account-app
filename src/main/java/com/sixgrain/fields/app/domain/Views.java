package com.sixgrain.fields.app.domain;

public class Views {

    public interface AccountId {}
    public interface AccountNameEmail {}

    public interface AccountFull extends AccountId, AccountNameEmail, FieldId {}

    public interface FieldId {}
    public interface FieldLatLonName {}

    public interface FieldFull extends FieldLatLonName, AccountNameEmail, FieldId {}

    public interface Ids extends AccountId, FieldId {}

}
