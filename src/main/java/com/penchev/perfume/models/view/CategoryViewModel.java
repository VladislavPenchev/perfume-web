package com.penchev.perfume.models.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CategoryViewModel extends BaseViewModel {
    private String name;
}
