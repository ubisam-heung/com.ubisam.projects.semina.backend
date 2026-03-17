package com.ubisam.projects.semina.backend.api.worlds;

import static io.u2ware.common.docs.MockMvcRestDocs.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.projects.semina.backend.domain.World;

@SpringBootTest
@AutoConfigureMockMvc
public class WorldTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private WorldDocs docs;

  @Autowired
  private WorldRepository worldRepository;

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

  @Test
  void contextLoad2() throws Exception {
    //Search 링크 확인
    mockMvc
      .perform(get("/api/worlds/search"))
      .andExpect(is2xx());
      
    // 40개의 Worlds
    List<World> worldList = docs.newEntities(40);
    worldRepository.saveAll(worldList);

    //40개 추가 후 목록 확인
    mockMvc
      .perform(get("/api/worlds"))
      .andDo(print())
      .andExpect(is2xx())
      .andExpect(isJson("$.page.totalElements", 40));

    //Search - 검색
    mockMvc
      .perform(get("/api/worlds/search/findByName")
      .param("name", "길동7"))
      .andExpect(is2xx())
      .andDo(print())
      .andExpect(isJson("$._embedded.worlds[0].name", "길동7"));

    //Search - 페이징 10개씩 4페이지
    mockMvc
      .perform(get("/api/worlds")
      .param("size", "10"))
      .andDo(print())
      .andExpect(is2xx())
      .andExpect(isJson("$.page.totalPages", 4))
      .andExpect(isJson("$.page.size", 10))
      .andExpect(isJson("$.page.totalElements", 40));

      //정렬 검증 - id 내림차순
      mockMvc
        .perform(get("/api/worlds")
        .param("sort", "id,desc"))
        .andDo(print())
        .andExpect(is2xx())
        .andExpect(isJson("$._embedded.worlds[0].id", 40));

  }
}
