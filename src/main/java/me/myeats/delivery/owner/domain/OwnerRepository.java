package me.myeats.delivery.owner.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<Owner> findOneWithAuthoritiesByName(String name);
}
