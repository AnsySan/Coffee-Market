package com.ansysan.coffeemarket.user.repository;

import com.ansysan.coffeemarket.user.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository  extends JpaRepository<Address, UUID> {
}