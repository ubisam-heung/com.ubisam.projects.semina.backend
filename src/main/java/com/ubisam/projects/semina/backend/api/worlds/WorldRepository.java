package com.ubisam.projects.semina.backend.api.worlds;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubisam.projects.semina.backend.domain.World;

public interface  WorldRepository extends JpaRepository<World, Long>{
    List<World> findByName(String name);
    // List<World> findByContinentAndPopulation(String continent, Long population);
    // List<World> findByIdOrName(Long id, String name);
    
}
