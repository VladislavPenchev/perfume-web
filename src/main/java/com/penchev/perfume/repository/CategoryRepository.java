package com.penchev.perfume.repository;

import com.penchev.perfume.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByNameAndIsActive(String name, boolean isActive);

    Optional<Category> findByIdAndIsActive(String id, boolean isActive);

    List<Category> findAllByIsActive(boolean isActive);
}
