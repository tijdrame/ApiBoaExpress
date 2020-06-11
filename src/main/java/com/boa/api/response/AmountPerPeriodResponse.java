package com.boa.api.response;

import java.util.ArrayList;
import java.util.List;

public class AmountPerPeriodResponse extends GenericResponse{
    private List<ItemPeriod> itemPeriods;

    public AmountPerPeriodResponse() {
    }

    public AmountPerPeriodResponse(List<ItemPeriod> itemPeriods) {
        this.itemPeriods = itemPeriods;
    }

    public List<ItemPeriod> getItemPeriods() {
        if(this.itemPeriods==null || this.itemPeriods.isEmpty()) this.itemPeriods = new ArrayList<>();
        return this.itemPeriods;
    }

    public void setItemPeriods(List<ItemPeriod> itemPeriods) {
        this.itemPeriods = itemPeriods;
    }

    public AmountPerPeriodResponse itemPeriods(List<ItemPeriod> itemPeriods) {
        this.itemPeriods = itemPeriods;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " itemPeriods='" + getItemPeriods() + "'" +
            "}";
    }

}