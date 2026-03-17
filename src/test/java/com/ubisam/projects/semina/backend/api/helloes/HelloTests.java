package com.ubisam.projects.semina.backend.api.helloes;

import static io.u2ware.common.docs.MockMvcRestDocs.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.projects.semina.backend.domain.Hello;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private HelloDocs docs;

  @Autowired
  private HelloRepository helloRepository;

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

  @Test
  void contextLoad2() throws Exception {

    // 40개의 Hello
    List<Hello> helloList = docs.newEntities(40);
    helloRepository.saveAll(helloList);

   // 40개 추가 후 목록 확인
    mockMvc
      .perform(post("/api/helloes/search")
      .content(docs::setKeyword, ""))
      .andExpect(is2xx())
      .andDo(print())
      .andExpect(isJson("$.page.totalElements", 40)); 

    // Search - 검색
    mockMvc
      .perform(post("/api/helloes/search")
      .content(docs::setKeyword, "철수17"))
      .andExpect(is2xx())
      .andDo(print())
      .andExpect(isJson("$._embedded.helloes[0].name", "철수17"));
    ;

    //Search - 페이징 5개씩 8페이지
    mockMvc
      .perform(post("/api/helloes/search")
      .content(docs::setKeyword, "")
      .param("size", "5"))
      .andDo(print())
      .andExpect(is2xx())
      .andExpect(isJson("$.page.totalPages", 8))
      .andExpect(isJson("$.page.size", 5))
      .andExpect(isJson("$.page.totalElements", 40));

    //정렬 검증 - 이름 내림차순
    mockMvc
      .perform(post("/api/helloes/search")
      .content(docs::setKeyword, "")
      .param("sort", "name,desc"))
      .andDo(print())
      .andExpect(is2xx())
      .andExpect(isJson("$._embedded.helloes[0].name", "철수9"));
  }
}
