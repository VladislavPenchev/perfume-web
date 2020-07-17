package com.penchev.perfume.models.view;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class ProductViewModel extends BaseViewModel {
    private String name;
    private BigDecimal price;
    private String description;
    private String videoUrl;
    private int discount;
    private String ean;
    private int qty;
    private CategoryViewModel category;
    private BrandViewModel brand;
    private List<RatingViewModel> ratings;
}
