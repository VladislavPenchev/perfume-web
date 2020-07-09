package com.penchev.perfume.service.impl;

import com.penchev.perfume.constants.ExceptionConstants;
import com.penchev.perfume.exception.exceptions.ProductNotFoundException;
import com.penchev.perfume.factories.ProductFactoryImpl;
import com.penchev.perfume.models.binding.PerfumeBindingModel;
import com.penchev.perfume.models.entities.Perfume;
import com.penchev.perfume.models.view.PerfumeViewModel;
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

    @Override
    public PerfumeViewModel createProduct(PerfumeBindingModel perfumeBindingModel) {
        Perfume perfume = null;
        perfume = (Perfume) ProductFactoryImpl.createProduct(perfumeBindingModel);

        perfume = perfumeRepository.save(perfume);

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
                .build();
    }

    @Override
    public PerfumeViewModel getOneProduct(String id) {
        Perfume perfume = this.perfumeRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format(ExceptionConstants.NOT_FOUND_PRODUCT_WITH_ID, id)));

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
                .build();
    }

    @Override
    public List<PerfumeViewModel> getAllProducts() {
        return perfumeRepository.findAll()
                .stream()
                .map(p -> PerfumeViewModel.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .price(p.getPrice())
                        .description(p.getDescription())
                        .videoUrl(p.getVideoUrl())
                        .discount(p.getDiscount())
                        .ean(p.getEan())
                        .qty(p.getQty())
                        .hasWrap(p.isHasWrap())
                        .aromaCombination(p.getAromaCombination())
                        .build())
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
        perfumeRepository.save(perfume);

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
                .build();
    }

}
