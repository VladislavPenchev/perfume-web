package com.penchev.perfume.service.impl;

import com.penchev.perfume.constants.ExceptionConstants;
import com.penchev.perfume.exception.exceptions.BrandNotFoundException;
import com.penchev.perfume.models.binding.BrandBindingModel;
import com.penchev.perfume.models.entities.Brand;
import com.penchev.perfume.models.view.BrandViewModel;
import com.penchev.perfume.repository.BrandRepository;
import com.penchev.perfume.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public BrandViewModel createBrand(BrandBindingModel brandBindingModel) {
        Brand brand = Brand.builder()
                .name(brandBindingModel.getName())
                .build();

        brand = brandRepository.save(brand);

        return convertBrandToBrandViewModel(brand);
    }

    @Override
    public List<BrandViewModel> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(this::convertBrandToBrandViewModel)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public BrandViewModel getOneBrand(String name) {
        Brand brand = checkIfBrandExist(name);

        return this.convertBrandToBrandViewModel(brand);
    }

    @Override
    public BrandViewModel editBrand(String name, BrandBindingModel brandBindingModel) {
        Brand brand = checkIfBrandExist(name);
        brand.setName(brandBindingModel.getName());
        brand = brandRepository.save(brand);

        return this.convertBrandToBrandViewModel(brand);
    }

    @Override
    public void deleteBrand(String name) {
        brandRepository.delete(checkIfBrandExist(name));
    }

    private Brand checkIfBrandExist(String name) {
        return brandRepository.findByName(name)
                .orElseThrow(() -> new BrandNotFoundException(String.format(ExceptionConstants.NOT_FOUND_BRAND_WITH_NAME, name)));
    }

    private BrandViewModel convertBrandToBrandViewModel(Brand brand) {
        return BrandViewModel.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
