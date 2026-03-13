package com.ubisam.projects.semina.backend.api.helloes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class HelloDocs extends MockMvcRestDocs{
  
  public Map<String, Object> newEntity(String name){
    Map<String, Object> hello = new HashMap<>();
    hello.put("name", name);
    hello.put("email", super.randomText("") + "@" + super.randomText("") + ".com");
    return hello;
  }

  public Map<String, Object> updateEntity(Map<String, Object> entity, String name){
    entity.put("name", name);
    return entity;
  }

  public Map<String, Object> setKeyword(String keyword){
    Map<String, Object> hello = new HashMap<>();
    hello.put("keyword", keyword);
    return hello;
  }
}

