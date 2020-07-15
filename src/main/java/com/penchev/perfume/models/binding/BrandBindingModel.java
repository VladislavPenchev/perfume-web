package com.penchev.perfume.models.binding;

import com.penchev.perfume.validate.ExistBrand;
import com.penchev.perfume.validator.ValidationGroups.ExistGroup;
import lombok.Data;

@Data
public class BrandBindingModel {

    @ExistBrand(message = "{brand.already.exist}", groups = ExistGroup.class)
    private String name;
}
