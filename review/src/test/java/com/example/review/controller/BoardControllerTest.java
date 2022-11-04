package com.example.review.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j
public class BoardControllerTest {

    @Autowired //우리가 직접 주입받아서 메모리에 올린다. 주입 요청하면 알아서 됨. bean에 등록되어 있는 객체라서.
   private WebApplicationContext webApplicationContext; //WAS가 관리하는 객체, 사용자의 요청을 처리해주는 애
                                    //서버 설정한 내용들이 다 들어가 있음.

//    브라우저에서 URL을 요청한 것과 같은 환경을 구성해준다.
    private MockMvc mockMvc;

    @BeforeEach //@Test 실행되기 전에 setUp 먼저 해라
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//  브라우저 환경을 구축해주는 MockMvcBuilders를 통해서 우리가 설정해놓은 서버의 설정들을 갖고 있는 webApplicationContext를 전달해주면
//  그걸로 mockMvc의 환경을 구축해준다.
    }

    @Test
    public void showTest() throws Exception{//경로가 잘못 될 수 있어서 throws 해주어야 한다.
                                 //요청객체를 만들어준다.                   //전달할 파라미터가 있다면 .param() 이어붙이면 된다.
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                .andReturn().getModelAndView().getModelMap().toString());
                 //요청을 다 보냈다는 뜻   //응답 부분) 모델전달자에 담아놓은 것을 확인할 수 있다.
        //html 파일이 있어야 오류가 안 난다.
    }

    @Test
    public void writeTest() throws Exception{
        //페이지 이동
//        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/write")).andReturn()
//                .getModelAndView().getViewName());
                                    //응답한 화면의 경로가 나옴. /board/write

        //DB insert
        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/write")
                .param("boardTitle", "새로운 제목1")
                .param("boardContent", "새로운 내용1")
                .param("boardWriter", "testC"))
                .andReturn().getModelAndView().getViewName());

    }

    @Test
    public void UpdateTest() throws Exception{
//        페이지 이동
//        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/update")
//                .param("boardNumber", "7")) //param의 value는 string이기 때문에 7l이 아니라 "7"
//                .andReturn().getModelAndView().getModelMap().toString());
//        DB update
        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/update")
                .param("boardNumber", "7")
                .param("boardTitle", "수정된 제목1")
                .param("boardContent", "수정된 내용1"))
                .andReturn().getModelAndView().getModelMap().toString());
    }

    @Test
    public void deleteTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/board/delete")
                .param("boardNumber", "7"))
//                .andExpect(MockMvcResultMatchers.status().isOk()); //일부러 오류 내기
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection()); //3으로 시작하는 redirect이니? > 성공
        //redirect는 3xx인데, Actual 부분에 3xx으로 나오면 성공

    }
}
