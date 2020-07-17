package com.penchev.perfume.models.binding;

import com.penchev.perfume.models.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PerfumeBindingModel extends ProductBindingModel {
    private String aromaCombination;

    private boolean hasWrap;
}
