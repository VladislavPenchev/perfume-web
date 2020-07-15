package com.penchev.perfume.service;

import com.penchev.perfume.models.binding.BrandBindingModel;
import com.penchev.perfume.models.view.BrandViewModel;

import java.util.List;

public interface BrandService {

    BrandViewModel createBrand(BrandBindingModel brandBindingModel);

    List<BrandViewModel> getAllBrands();

    BrandViewModel getOneBrand(String name);

    BrandViewModel editBrand(String name, BrandBindingModel brandBindingModel);

    void deleteBrand(String name);
}
