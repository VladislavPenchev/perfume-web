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

import java.util.Date;
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
                .isActive(true)
                .createdTimestamp(new Date())
                .updatedTimestamp(new Date())
                .version(0)
                .build();
        brand = brandRepository.save(brand);

        return convertBrandToBrandViewModel(brand);
    }

    @Override
    public List<BrandViewModel> getAllBrands() {
        return brandRepository.findAllByIsActive(true)
                .stream()
                .map(this::convertBrandToBrandViewModel)
                .collect(Collectors.toUnmodifiableList());
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
        Brand brand = checkIfBrandExist(name);
        brand.setActive(false);
        brandRepository.save(brand);
    }

    private Brand checkIfBrandExist(String name) {
        return brandRepository.findByNameAndIsActive(name, true)
                .orElseThrow(() -> new BrandNotFoundException(String.format(ExceptionConstants.NOT_FOUND_BRAND_WITH_NAME, name)));
    }

    private BrandViewModel convertBrandToBrandViewModel(Brand brand) {
        return BrandViewModel.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
