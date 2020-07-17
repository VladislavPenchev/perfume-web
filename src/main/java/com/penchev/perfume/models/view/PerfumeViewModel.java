package com.penchev.perfume.models.view;

import com.penchev.perfume.models.entities.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PerfumeViewModel extends ProductViewModel {
    private String aromaCombination;
    private boolean hasWrap;
}
