package com.penchev.perfume.models.binding;

import com.penchev.perfume.validate.ExistCategory;
import com.penchev.perfume.validator.ValidationGroups.ExistGroup;
import lombok.Data;

@Data
public class CategoryBindingModel {

    @ExistCategory(message = "{category.already.exist}", groups = ExistGroup.class)
    private String name;
}
