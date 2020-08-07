package com.penchev.perfume.service;

import com.penchev.perfume.models.binding.CategoryBindingModel;
import com.penchev.perfume.models.view.CategoryViewModel;

import java.util.List;

public interface CategoryService {

    CategoryViewModel createCategory(CategoryBindingModel categoryBindingModel);

    List<CategoryViewModel> getAllCategories();

    void deleteCategory(String name);

    CategoryViewModel editCategory(String name, CategoryBindingModel categoryBindingModel);
}
