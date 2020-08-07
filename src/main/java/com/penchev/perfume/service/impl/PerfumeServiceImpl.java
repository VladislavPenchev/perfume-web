package com.penchev.perfume.service.impl;

import com.penchev.perfume.constants.ExceptionConstants;
import com.penchev.perfume.exception.exceptions.BrandNotFoundException;
import com.penchev.perfume.exception.exceptions.CategoryNotFoundException;
import com.penchev.perfume.exception.exceptions.ProductNotFoundException;
import com.penchev.perfume.factories.ProductFactoryImpl;
import com.penchev.perfume.models.binding.PerfumeBindingModel;
import com.penchev.perfume.models.entities.Brand;
import com.penchev.perfume.models.entities.Category;
import com.penchev.perfume.models.entities.Perfume;
import com.penchev.perfume.models.view.BrandViewModel;
import com.penchev.perfume.models.view.CategoryViewModel;
import com.penchev.perfume.models.view.PerfumeViewModel;
import com.penchev.perfume.repository.BrandRepository;
import com.penchev.perfume.repository.CategoryRepository;
import com.penchev.perfume.repository.PerfumeRepository;
import com.penchev.perfume.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfumeServiceImpl implements PerfumeService {

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public PerfumeViewModel createProduct(PerfumeBindingModel perfumeBindingModel) {
        Perfume perfume = null;

        Category category = checkIfCategoryExist(perfumeBindingModel.getCategory());

        Brand brand = checkIfBrandExist(perfumeBindingModel.getBrand());

        perfume = (Perfume) ProductFactoryImpl.createProduct(perfumeBindingModel, category, brand);

        perfume = perfumeRepository.save(perfume);

        return convertPerfumeToPerfumeViewModel(perfume);
    }

    @Override
    public PerfumeViewModel getOneProduct(String id) {
        Perfume perfume = this.perfumeRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format(ExceptionConstants.NOT_FOUND_PRODUCT_WITH_ID, id)));

        return convertPerfumeToPerfumeViewModel(perfume);
    }

    @Override
    public List<PerfumeViewModel> getAllProducts() {

        return perfumeRepository.findAll()
                .stream()
                .map(this::convertPerfumeToPerfumeViewModel)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void deleteProduct(String id) {
        perfumeRepository.deleteById(id);
    }

    @Override
    public PerfumeViewModel editPerfumeViewModel(String id, PerfumeBindingModel perfumeBindingModel) {
        Perfume perfume = perfumeRepository.getOne(id);

        Category category = checkIfCategoryExist(perfumeBindingModel.getCategory());

        Brand brand = checkIfBrandExist(perfumeBindingModel.getBrand());

        perfume.setName(perfumeBindingModel.getName());
        perfume.setPrice(new BigDecimal(perfumeBindingModel.getPrice()));
        perfume.setDescription(perfumeBindingModel.getDescription());
        perfume.setVideoUrl(perfumeBindingModel.getVideoUrl());
        perfume.setDiscount(Integer.parseInt(perfumeBindingModel.getDiscount()));
        perfume.setEan(perfumeBindingModel.getEan());
        perfume.setQuantity(Integer.parseInt(perfumeBindingModel.getQuantity()));
        perfume.setAromaCombination(perfumeBindingModel.getAromaCombination());
        perfume.setHasWrap(perfumeBindingModel.isHasWrap());
        perfume.setCategoryId(category.getId());
        perfume.setBrandId(brand.getId());
        perfumeRepository.save(perfume);

        return convertPerfumeToPerfumeViewModel(perfume);
    }

//    @Override
//    public List<PerfumeViewModel> getAllPerfumesByCategories(String category) {
//        checkIfCategoryExist(category);
//
//        return perfumeRepository.findAllByCategory_Name(category)
//                .stream()
//                .map(this::convertPerfumeToPerfumeViewModel)
//                .collect(Collectors.toUnmodifiableList());
//    }
//
//    @Override
//    public List<PerfumeViewModel> getAllPerfumesByCategoryAndLowestPrice(String category) {
//        checkIfCategoryExist(category);
//
//        return perfumeRepository.findAllByPerfumesLowestPrice(category)
//                .stream()
//                .map(this::convertPerfumeToPerfumeViewModel)
//                .collect(Collectors.toUnmodifiableList());
//    }
//
//    @Override
//    public List<PerfumeViewModel> getAllPerfumesByCategoryAndGreatestPrice(String category) {
//        checkIfCategoryExist(category);
//
//        return perfumeRepository.findAllByPerfumesGreatestPrice(category)
//                .stream()
//                .map(this::convertPerfumeToPerfumeViewModel)
//                .collect(Collectors.toUnmodifiableList());
//    }
//
//    @Override
//    public List<PerfumeViewModel> getAllPerfumesByCategorySortByName(String category) {
//        checkIfCategoryExist(category);
//
//        return perfumeRepository.findAllByPerfumesOrderByPerfumesName(category)
//                .stream()
//                .map(this::convertPerfumeToPerfumeViewModel)
//                .collect(Collectors.toUnmodifiableList());
//    }

    private Brand checkIfBrandExist(String brandName) {
        return brandRepository.findByNameAndIsActive(brandName, true)
                .orElseThrow(() -> new BrandNotFoundException(String.format(ExceptionConstants.NOT_FOUND_BRAND_WITH_NAME, brandName)));
    }

    private Brand checkIfBrandExistById(String brandId) {
        return brandRepository.findByIdAndIsActive(brandId, true)
                .orElseThrow(() -> new BrandNotFoundException(String.format(ExceptionConstants.NOT_FOUND_BRAND_WITH_ID, brandId)));
    }

    private Category checkIfCategoryExist(String categoryName) {
        return categoryRepository.findByNameAndIsActive(categoryName, true)
                .orElseThrow(() -> new CategoryNotFoundException(String.format(ExceptionConstants.NOT_FOUND_CATEGORY_WITH_NAME, categoryName)));
    }

    private Category checkIfCategoryExistById(String categoryId ) {
        return categoryRepository.findByIdAndIsActive(categoryId, true)
                .orElseThrow(() -> new CategoryNotFoundException(String.format(ExceptionConstants.NOT_FOUND_CATEGORY_WITH_ID, categoryId)));
    }

    private PerfumeViewModel convertPerfumeToPerfumeViewModel(Perfume perfume) {

        Category category = checkIfCategoryExistById(perfume.getCategoryId());

        Brand brand = checkIfBrandExistById(perfume.getBrandId());

        CategoryViewModel categoryViewModel = CategoryViewModel.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

        BrandViewModel brandViewModel = BrandViewModel.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();

        return PerfumeViewModel.builder()
                .id(perfume.getId())
                .name(perfume.getName())
                .price(perfume.getPrice())
                .description(perfume.getDescription())
                .videoUrl(perfume.getVideoUrl())
                .discount(perfume.getDiscount())
                .ean(perfume.getEan())
                .quantity(perfume.getQuantity())
                .aromaCombination(perfume.getAromaCombination())
                .hasWrap(perfume.isHasWrap())
                .category(categoryViewModel)
                .brand(brandViewModel)
                .build();
    }
}
