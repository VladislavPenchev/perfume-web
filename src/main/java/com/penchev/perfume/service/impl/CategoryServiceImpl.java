package com.penchev.perfume.service.impl;

import com.penchev.perfume.constants.ExceptionConstants;
import com.penchev.perfume.exception.exceptions.CategoryNotFoundException;
import com.penchev.perfume.models.binding.CategoryBindingModel;
import com.penchev.perfume.models.entities.Category;
import com.penchev.perfume.models.view.CategoryViewModel;
import com.penchev.perfume.repository.CategoryRepository;
import com.penchev.perfume.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryViewModel createCategory(CategoryBindingModel categoryBindingModel) {
        Category category = new Category();
        category.setName(categoryBindingModel.getName());

        category = categoryRepository.save(category);

        return CategoryViewModel.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public CategoryViewModel getOneCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException(String.format(ExceptionConstants.NOT_FOUND_CATEGORY_WITH_NAME, name)));

        return CategoryViewModel.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public List<CategoryViewModel> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(c -> {
                    return CategoryViewModel.builder()
                            .id(c.getId())
                            .name(c.getName())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException(String.format(ExceptionConstants.NOT_FOUND_CATEGORY_WITH_NAME, name)));

        categoryRepository.delete(category);
    }

    @Override
    public CategoryViewModel editCategory(String name, CategoryBindingModel categoryBindingModel) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException(String.format(ExceptionConstants.NOT_FOUND_CATEGORY_WITH_NAME, name)));

        category.setName(categoryBindingModel.getName());

        return CategoryViewModel.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
