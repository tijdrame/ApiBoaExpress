package com.boa.api.response;

import java.util.ArrayList;
import java.util.List;

public class AmountPerCountryResponse extends GenericResponse{
    private List<ItemCountry> itemCountries;

    public AmountPerCountryResponse() {
    }

    public AmountPerCountryResponse(List<ItemCountry> itemCountries) {
        this.itemCountries = itemCountries;
    }

    public List<ItemCountry> getItemCountries() {
        if(this.itemCountries==null || this.itemCountries.isEmpty()) this.itemCountries = new ArrayList<>();
        return this.itemCountries;
    }

    public void setItemCountries(List<ItemCountry> itemCountries) {
        this.itemCountries = itemCountries;
    }

    public AmountPerCountryResponse itemCountries(List<ItemCountry> itemCountries) {
        this.itemCountries = itemCountries;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " itemCountries='" + getItemCountries() + "'" +
            "}";
    }

}