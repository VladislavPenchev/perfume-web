package com.penchev.perfume.service.impl;

import com.penchev.perfume.constants.ExceptionConstants;
import com.penchev.perfume.exception.exceptions.CategoryNotFoundException;
import com.penchev.perfume.exception.exceptions.ProductNotFoundException;
import com.penchev.perfume.factories.ProductFactoryImpl;
import com.penchev.perfume.models.binding.PerfumeBindingModel;
import com.penchev.perfume.models.entities.Category;
import com.penchev.perfume.models.entities.Perfume;
import com.penchev.perfume.models.view.CategoryViewModel;
import com.penchev.perfume.models.view.PerfumeViewModel;
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

    @Override
    public PerfumeViewModel createProduct(PerfumeBindingModel perfumeBindingModel) {
        Perfume perfume = null;

        Category category = categoryRepository.findByName(perfumeBindingModel.getCategory().getName())
                .orElseThrow(() -> new CategoryNotFoundException(String.format(ExceptionConstants.NOT_FOUND_CATEGORY_WITH_NAME, perfumeBindingModel.getCategory().getName())));

        perfume = (Perfume) ProductFactoryImpl.createProduct(perfumeBindingModel, category);

        perfume = perfumeRepository.save(perfume);

        return getPerfumeViewModel(perfume, perfume.getCategory());
    }

    @Override
    public PerfumeViewModel getOneProduct(String id) {
        Perfume perfume = this.perfumeRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format(ExceptionConstants.NOT_FOUND_PRODUCT_WITH_ID, id)));

        return getPerfumeViewModel(perfume, perfume.getCategory());
    }

    @Override
    public List<PerfumeViewModel> getAllProducts() {

        return perfumeRepository.findAll()
                .stream()
                .map(p -> getPerfumeViewModel(p, p.getCategory()))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void deleteProduct(String id) {

        Perfume perfume = perfumeRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format(ExceptionConstants.NOT_FOUND_PRODUCT_WITH_ID, id)));

        perfumeRepository.deleteById(id);
    }

    @Override
    public PerfumeViewModel editPerfumeViewModel(String id, PerfumeBindingModel perfumeBindingModel) {
        Perfume perfume = perfumeRepository.getOne(id);

        perfume.setName(perfumeBindingModel.getName());
        perfume.setPrice(new BigDecimal(perfumeBindingModel.getPrice()));
        perfume.setDescription(perfumeBindingModel.getDescription());
        perfume.setVideoUrl(perfumeBindingModel.getVideoUrl());
        perfume.setDiscount(Integer.parseInt(perfumeBindingModel.getDiscount()));
        perfume.setEan(perfumeBindingModel.getEan());
        perfume.setQty(Integer.parseInt(perfumeBindingModel.getQty()));
        perfume.setAromaCombination(perfumeBindingModel.getAromaCombination());
        perfume.setHasWrap(perfumeBindingModel.isHasWrap());
        perfume.setCategory(perfumeBindingModel.getCategory());
        perfumeRepository.save(perfume);

        return getPerfumeViewModel(perfume, perfume.getCategory());
    }

    private PerfumeViewModel getPerfumeViewModel(Perfume perfume, Category category) {
        CategoryViewModel categoryViewModel = CategoryViewModel.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

        return PerfumeViewModel.builder()
                .id(perfume.getId())
                .name(perfume.getName())
                .price(perfume.getPrice())
                .description(perfume.getDescription())
                .videoUrl(perfume.getVideoUrl())
                .discount(perfume.getDiscount())
                .ean(perfume.getEan())
                .qty(perfume.getQty())
                .aromaCombination(perfume.getAromaCombination())
                .hasWrap(perfume.isHasWrap())
                .category(categoryViewModel)
                .build();
    }

}
