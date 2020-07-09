package com.penchev.perfume.models.view;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


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

}
