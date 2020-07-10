package com.penchev.perfume.service;

import com.penchev.perfume.models.binding.PerfumeBindingModel;
import com.penchev.perfume.models.view.PerfumeViewModel;

import java.util.List;

public interface PerfumeService {

    PerfumeViewModel createProduct(PerfumeBindingModel productBindingModel);

    PerfumeViewModel getOneProduct(String id);

    List<PerfumeViewModel> getAllProducts();

    void deleteProduct(String id);

    PerfumeViewModel editPerfumeViewModel(String id, PerfumeBindingModel perfumeBindingModel);

    List<PerfumeViewModel> getAllPerfumesByCategories(String category);

    List<PerfumeViewModel> getAllPerfumesByCategoryAndLowestPrice(String category);

    List<PerfumeViewModel> getAllPerfumesByCategoryAndGreatestPrice(String category);
}
