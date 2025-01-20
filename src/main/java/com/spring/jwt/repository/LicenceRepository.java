package com.spring.jwt.repository;

import com.spring.jwt.entity.Licence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LicenceRepository extends JpaRepository<Licence, UUID> {

    Licence getById(UUID licenceId);

  //  List<Licence> findByProductNameContainingIgnoreCaseOrderByProductNameAsc(String name);
}
