package com.penchev.perfume.models.binding;

import lombok.Data;

@Data
public class RatingBindingModel {
    private int stars;
    private String opinion;
    private String userName;
}
