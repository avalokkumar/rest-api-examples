package com.rest.appstore.model;

import lombok.Data;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class FilterCriteria {

    @NotBlank
    private List<String> fields;

    @Min(0)
    private int offset;
    @Min(1)
    private int limit;
}
