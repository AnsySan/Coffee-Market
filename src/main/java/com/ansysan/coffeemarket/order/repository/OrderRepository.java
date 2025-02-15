package com.ansysan.coffeemarket.order.repository;

import com.ansysan.coffeemarket.openapi.dto.OrderStatus;
import com.ansysan.coffeemarket.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query(value = "SELECT o FROM Order o WHERE o.userId = :userId AND o.status IN :statusList")
    List<Order> findAllByUserIdAndStatus(@Param("userId") UUID userId,
                                         @Param("statusList") List<OrderStatus> statusList);

    Optional<Order> findByUserIdAndSessionId(UUID userId, String sessionId);
}