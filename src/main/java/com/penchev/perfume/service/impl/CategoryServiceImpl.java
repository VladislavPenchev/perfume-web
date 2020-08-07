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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryViewModel createCategory(CategoryBindingModel categoryBindingModel) {
        Category category = Category.builder()
                .name(categoryBindingModel.getName())
                .isActive(true)
                .createdTimestamp(new Date())
                .updatedTimestamp(new Date())
                .version(0)
                .build();

        category = categoryRepository.save(category);

        return convertCategoryToCategoryViewModel(category);
    }

    @Override
    public List<CategoryViewModel> getAllCategories() {
        return categoryRepository.findAllByIsActive(true)
                .stream()
                .map(this::convertCategoryToCategoryViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryViewModel editCategory(String name, CategoryBindingModel categoryBindingModel) {
        Category category = checkIfCategoryExist(name);
        category.setName(categoryBindingModel.getName());
        category = categoryRepository.save(category);

        return convertCategoryToCategoryViewModel(category);
    }

    @Override
    public void deleteCategory(String name) {
        Category category = checkIfCategoryExist(name);
        category.setActive(false);
        categoryRepository.save(category);
    }

    private Category checkIfCategoryExist(String name) {
        return categoryRepository.findByNameAndIsActive(name, true)
                .orElseThrow(() -> new CategoryNotFoundException(String.format(ExceptionConstants.NOT_FOUND_CATEGORY_WITH_NAME, name)));
    }

    private CategoryViewModel convertCategoryToCategoryViewModel(Category category) {
        return CategoryViewModel.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
