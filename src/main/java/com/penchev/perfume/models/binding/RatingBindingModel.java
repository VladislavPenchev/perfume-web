package com.penchev.perfume.models.binding;

import com.penchev.perfume.validator.ValidationGroups.LengthGroup;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RatingBindingModel {

    @NotNull(message = "{rating.stars.empty}")
    @Min(value = 1, message = "{rating.stars.size.min}", groups = LengthGroup.class)
    @Max(value = 5, message = "{rating.stars.size.max}", groups = LengthGroup.class)
    private int stars;

    @NotBlank(message = "{rating.opinion.empty}")
    @Size.List({
            @Size(max = 500, message = "{rating.opinion.size.max}", groups = LengthGroup.class)
    })
    private String opinion;
}
