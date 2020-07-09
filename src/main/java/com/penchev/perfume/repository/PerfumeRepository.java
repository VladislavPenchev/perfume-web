package com.penchev.perfume.repository;

import com.penchev.perfume.models.entities.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, String> {
    Optional<Perfume> findByName(String name);
}
