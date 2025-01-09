package com.spring.jwt.repository;

import com.spring.jwt.entity.Licence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdministratorRepository extends JpaRepository<Licence, UUID> {
}
