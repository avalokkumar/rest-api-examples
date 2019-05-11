package com.rest.appstore.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SortCriteria {

    @NotNull
    private SortOrder order;
    @NotBlank
    private String sortAttribute;
}


enum SortOrder {
    ASC("asc"), DESC("desc");

    SortOrder(String order) {
        this.order = order;
    }

    private String order;

    public String getOrder() {
        return this.order;
    }
}