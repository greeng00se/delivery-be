package me.myeats.delivery.customer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findOneByName(String name);

    boolean existsByName(String name);
}
