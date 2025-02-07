package com.ansysan.coffeemarket.favorite.repository;

import com.ansysan.coffeemarket.favorite.entity.FavoriteItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FavoriteItemRepository extends JpaRepository<FavoriteItemEntity, UUID> {
}