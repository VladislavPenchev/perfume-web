package com.penchev.perfume.repository;

import com.penchev.perfume.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByUserIdAndIsActive(String userId, boolean isActive);

    Optional<Order> findByIdAndIsActive(String orderId, boolean isActive);
}
