package com.penchev.perfume.models.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RatingViewModel extends BaseViewModel {
    private int stars;
    private String opinion;
    private String userName;
}
