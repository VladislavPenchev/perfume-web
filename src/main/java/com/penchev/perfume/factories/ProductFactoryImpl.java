package com.penchev.perfume.factories;

import com.penchev.perfume.models.binding.PerfumeBindingModel;
import com.penchev.perfume.models.binding.ProductBindingModel;
import com.penchev.perfume.models.entities.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class ProductFactoryImpl<T> {

    private ProductFactoryImpl() {

    }

    public static <T extends ProductBindingModel> Product createProduct(final T productBindingModel, Category category, Brand brand) {

        if (productBindingModel instanceof PerfumeBindingModel) {

            return new Perfume(productBindingModel.getName(),
                    new BigDecimal(productBindingModel.getPrice()),
                    productBindingModel.getDescription(),
                    productBindingModel.getVideoUrl(),
                    Integer.parseInt(productBindingModel.getDiscount()),
                    productBindingModel.getEan(),
                    Integer.parseInt(productBindingModel.getQty()),
                    ((PerfumeBindingModel) productBindingModel).getAromaCombination(),
                    ((PerfumeBindingModel) productBindingModel).isHasWrap(),
                    category,
                    brand,
                    new ArrayList<>());
        }

        return null;
    }
}