package com.penchev.perfume.factories;

import com.penchev.perfume.models.binding.PerfumeBindingModel;
import com.penchev.perfume.models.binding.ProductBindingModel;
import com.penchev.perfume.models.entities.Category;
import com.penchev.perfume.models.entities.Perfume;
import com.penchev.perfume.models.entities.Product;

import java.math.BigDecimal;

public final class ProductFactoryImpl<T> {

    private ProductFactoryImpl() {

    }

    public static <T extends ProductBindingModel> Product createProduct(final T productBindingModel, Category category) {

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
                    category);
        }

        return null;
    }
}