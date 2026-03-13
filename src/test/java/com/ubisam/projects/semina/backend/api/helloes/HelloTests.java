package com.ubisam.projects.semina.backend.api.helloes;

import static io.u2ware.common.docs.MockMvcRestDocs.*;


import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private HelloDocs docs;

  @Test
	void contextLoad() throws Exception {
		//perform : 요청 구간
		//andDo : 응답 구간(결과가 나오고 나서 무언가를 하고 싶을때)
		//andExpect : 검증 구간(결과를 기대하는 구간)

		// RestfulJpaRepository
		mockMvc
      .perform(get("/api/helloes"))
      .andExpect(is4xx())
    ;
    
		mockMvc
      .perform(get("/api/helloes/search"))
      .andExpect(is4xx())
    ;

		mockMvc
      .perform(post("/api/helloes/search"))
      .andExpect(is2xx())
    ;

		//Create
    mockMvc
      .perform(post("/api/helloes")
      .content(docs::newEntity, "김길동"))
      .andDo(print())
      .andExpect(is2xx())
      .andDo(result(docs::context , "entity1"))
    ;

		//Read
    String url = docs.context("entity1", "$._links.self.href");
    mockMvc
      .perform(post(url))
      .andDo(print())
      .andExpect(is2xx())
    ;
    mockMvc
      .perform(get(url))
      .andDo(print())
      .andExpect(is4xx())
    ;
    
		//Update
    Map<String,Object> body = docs.context("entity1");
		mockMvc
      .perform(put(url)
      .content(docs::updateEntity, body, "박길동"))
      .andExpect(is2xx())
      .andDo(print())
    ; 

		//Delete
    mockMvc
      .perform(delete(url))
      .andDo(print())
      .andExpect(is2xx())
    ;
	}
}
