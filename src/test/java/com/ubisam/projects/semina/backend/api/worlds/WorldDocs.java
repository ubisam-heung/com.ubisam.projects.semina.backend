package com.ubisam.projects.semina.backend.api.worlds;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

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
}

