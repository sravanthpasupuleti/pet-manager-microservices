package com.sravanth.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sravanth.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

}