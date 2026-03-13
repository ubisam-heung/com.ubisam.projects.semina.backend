package com.ubisam.projects.semina.backend.api.worlds;

import static io.u2ware.common.docs.MockMvcRestDocs.*;


import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WorldTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private WorldDocs docs;

  @Test
	void contextLoad() throws Exception {
		//perform : 요청 구간
		//andDo : 응답 구간(결과가 나오고 나서 무언가를 하고 싶을때)
		//andExpect : 검증 구간(결과를 기대하는 구간)

		// JpaRepository
		mockMvc
      .perform(get("/api/worlds"))
      .andExpect(is2xx())
    ;
    
		mockMvc
      .perform(get("/api/worlds/search"))
      .andExpect(is4xx())
    ;

		mockMvc
      .perform(post("/api/worlds/search"))
      .andExpect(is4xx())
    ;

		//Create
    mockMvc
      .perform(post("/api/worlds")
      .content(docs::newEntity, "김길동"))
      .andDo(print())
      .andExpect(is2xx())
      .andDo(result(docs::context , "entity1"))
    ;

		//Read
    String url = docs.context("entity1", "$._links.self.href");
    mockMvc
      .perform(get(url))
      .andDo(print())
      .andExpect(is2xx())
    ;
    mockMvc
      .perform(post(url))
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
