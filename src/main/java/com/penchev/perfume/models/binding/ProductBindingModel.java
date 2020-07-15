package com.penchev.perfume.models.binding;

import com.penchev.perfume.validate.ExistPerfume;
import com.penchev.perfume.validator.ValidationGroups.ExistGroup;
import com.penchev.perfume.validator.ValidationGroups.LengthGroup;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ProductBindingModel {
    @NotBlank(message = "{product.name.empty}")
    @Size.List({
            @Size(min = 6, message = "{product.size.min}", groups = LengthGroup.class),
            @Size(max = 60, message = "{product.size.max}", groups = LengthGroup.class)
    })
    @ExistPerfume(message = "{product.already.exist}", groups = ExistGroup.class)
    private String name;

    @NotBlank(message = "{product.price.empty}")
    @Digits(integer = 6, fraction = 2, message = "{product.price.size}", groups = LengthGroup.class)
    private String price;

    @Size.List({
            @Size(max = 500, message = "{product.size.max}", groups = LengthGroup.class)
    })
    private String description;

    private String videoUrl;

    @Digits(integer = 6, fraction = 2, message = "{product.discount.size}", groups = LengthGroup.class)
    private String discount;

    @NotBlank(message = "{product.ean.empty}")
    @Size.List({
            @Size(min = 13, message = "{product.ean.size.min}", groups = LengthGroup.class),
            @Size(max = 13, message = "{product.ean.size.max}", groups = LengthGroup.class)
    })
    private String ean;

    @NotBlank(message = "{product.qty.empty}")
    @Min(value = 1, message = "{product.qty.size.min}", groups = LengthGroup.class)
    @Max(value = 1000, message = "{product.qty.size.max}", groups = LengthGroup.class)
    private String qty;

    private String type;
}
