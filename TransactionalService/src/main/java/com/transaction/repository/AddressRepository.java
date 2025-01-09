package com.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transaction.entity.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
