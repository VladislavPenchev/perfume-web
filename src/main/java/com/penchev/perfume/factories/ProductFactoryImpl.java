package com.penchev.perfume.factories;

import com.penchev.perfume.models.binding.PerfumeBindingModel;
import com.penchev.perfume.models.binding.ProductBindingModel;
import com.penchev.perfume.models.entities.Brand;
import com.penchev.perfume.models.entities.Category;
import com.penchev.perfume.models.entities.Perfume;
import com.penchev.perfume.models.entities.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ProductFactoryImpl<T> {

    private ProductFactoryImpl() {

    }

    public static <T extends ProductBindingModel> Product createProduct(final T productBindingModel, Category category, Brand brand) {

        if (productBindingModel instanceof PerfumeBindingModel) {
            Perfume perfume = new Perfume(productBindingModel.getName(),
                    new BigDecimal(productBindingModel.getPrice()).setScale(2, RoundingMode.CEILING),
                    productBindingModel.getDescription(),
                    productBindingModel.getVideoUrl(),
                    Integer.parseInt(productBindingModel.getDiscount()),
                    productBindingModel.getEan(),
                    Integer.parseInt(productBindingModel.getQuantity()),
                    ((PerfumeBindingModel) productBindingModel).getAromaCombination(),
                    ((PerfumeBindingModel) productBindingModel).isHasWrap(),
                    category.getId(),
                    brand.getId());
            perfume.setActive(true);
            return perfume;
        }

        return null;
    }
}