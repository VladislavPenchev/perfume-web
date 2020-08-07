package com.penchev.perfume.utils;

import com.penchev.perfume.models.entities.Product;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ProductTypesUtil {

    public static List<String> getAllProductTypes() {
        List<String> productTypes = new ArrayList<>();

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(Product.class));

        try {
            Set<BeanDefinition> components = provider.findCandidateComponents("com/penchev/perfume/models/entities");
            for (BeanDefinition component : components) {
                Class currentClass = Class.forName(component.getBeanClassName());
                productTypes.add(currentClass.getSimpleName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Collections.unmodifiableList(productTypes);
    }
}


