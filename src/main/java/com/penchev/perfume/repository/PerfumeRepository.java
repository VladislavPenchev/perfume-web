package com.penchev.perfume.repository;

import com.penchev.perfume.models.entities.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, String> {
    Optional<Perfume> findByName(String name);

    List<Perfume> findAllByCategory_Name(String name);

    @Query("select p from com.penchev.perfume.models.entities.Perfume p " +
            "left join p.category c where c.name = :name order by p.price asc")
    List<Perfume> findAllByPerfumesLowestPrice(@Param("name") String name);

    @Query("select p from com.penchev.perfume.models.entities.Perfume p " +
            "left join p.category c where c.name = :name order by p.price desc")
    List<Perfume> findAllByPerfumesGreatestPrice(@Param("name") String name);

    @Query("select p from com.penchev.perfume.models.entities.Perfume p " +
            "left join p.category c where c.name = :name order by p.name")
    List<Perfume> findAllByPerfumesOrderByPerfumesName(@Param("name") String name);
}
