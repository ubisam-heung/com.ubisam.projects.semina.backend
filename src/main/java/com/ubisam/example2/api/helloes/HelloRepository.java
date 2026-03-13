package com.ubisam.example2.api.helloes;

import com.ubisam.example2.domain.Hello;

import io.u2ware.common.data.jpa.repository.RestfulJpaRepository;

public interface HelloRepository extends RestfulJpaRepository<Hello, Long>{
    // public interface HelloRepository extends JpaRepository<Hello, Long>{
    // List<Hello> findByEmail(String email);
    // List<Hello> findByNameAndEmail(String name, String email);
    // List<Hello> findByIdOrName(Long id, String name);

}
