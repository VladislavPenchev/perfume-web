package com.penchev.perfume.repository;

import com.penchev.perfume.models.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {

    Optional<Brand> findByNameAndIsActive(String name, boolean isActive);

    Optional<Brand> findByIdAndIsActive(String id, boolean isActive);

    List<Brand> findAllByIsActive(boolean isActive);
}
