package com.penchev.perfume.repository;

import com.penchev.perfume.models.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {
    Optional<ShoppingCart> findByUserId(String id);
}
