package com.ubisam.projects.semina.backend.api.helloes;

import com.ubisam.projects.semina.backend.domain.Hello;

import io.u2ware.common.data.jpa.repository.RestfulJpaRepository;

public interface HelloRepository extends RestfulJpaRepository<Hello, Long>{
    // public interface HelloRepository extends JpaRepository<Hello, Long>{
    // List<Hello> findByEmail(String email);
    // List<Hello> findByNameAndEmail(String name, String email);
    // List<Hello> findByIdOrName(Long id, String name);

}
