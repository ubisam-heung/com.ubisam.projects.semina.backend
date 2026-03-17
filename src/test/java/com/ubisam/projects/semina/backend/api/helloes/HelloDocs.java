package com.ubisam.projects.semina.backend.api.helloes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.ubisam.projects.semina.backend.domain.Hello;

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

  public List<Hello> newEntities(int count) {
  return IntStream.rangeClosed(1, count)
    .mapToObj(i -> newEntity("철수" + i))
    .map(entityMap -> {
      Hello hello = new Hello();
      hello.setName((String) entityMap.get("name"));
      hello.setEmail((String) entityMap.get("email"));
      return hello;
    })
    .toList();
  }

  public Map<String, Object> setKeyword(String keyword){
    Map<String, Object> hello = new HashMap<>();
    hello.put("keyword", keyword);
    return hello;
  }
}

