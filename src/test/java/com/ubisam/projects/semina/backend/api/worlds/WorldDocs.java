package com.ubisam.projects.semina.backend.api.worlds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.ubisam.projects.semina.backend.domain.World;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class WorldDocs extends MockMvcRestDocs{
  
  public Map<String, Object> newEntity(String name){
    Map<String, Object> world = new HashMap<>();
    world.put("name", name);
    world.put("email", super.randomText("") + "@" + super.randomText("") + ".com");
    return world;
  }

  public Map<String, Object> updateEntity(Map<String, Object> entity, String name){
    entity.put("name", name);
    return entity;
  }

  public List<World> newEntities(int count) {
    return IntStream.rangeClosed(1, count)
      .mapToObj(i -> newEntity("길동" + i))
      .map(entityMap -> {
        World world = new World();
        world.setName((String) entityMap.get("name"));
        world.setEmail((String) entityMap.get("email"));
        return world;
      })
      .toList();
  }

}

