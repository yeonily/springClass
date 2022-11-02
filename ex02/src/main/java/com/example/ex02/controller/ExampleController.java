package com.example.ex02.controller;

import com.example.ex02.domain.vo.MemberVO;
import com.example.ex02.domain.vo.ProductVO;
import com.example.ex02.domain.vo.TaskVO;
import com.example.ex02.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/ex/*")
public class ExampleController {

    @RequestMapping(value = "ex01", method = {RequestMethod.GET, RequestMethod.POST})
    public void ex01(){
        log.info("ex01...........");
    }

    @GetMapping("ex02")
    public void ex02(HttpServletRequest request){
        log.info("name: " + request.getParameter("name"));
    }

    @GetMapping("ex03")
    public void ex03(String name){
        log.info("name: " + name);
    }

    @GetMapping("ex04")
    public void ex04(MemberVO memberVO){
        log.info("member: " + memberVO);
    }

    @GetMapping("ex05")
    public void ex05(@RequestParam("id") String name, int age){
        log.info("name : " + name);
        log.info("age : " + age);
    }

    @GetMapping("ex06")
    public void ex06(MemberVO memberVO){
        log.info("ex06.................");
    }

    @GetMapping("ex07")
    public void ex07(@ModelAttribute("gender") String gender, MemberVO memberVO){
        log.info("ex07.................");
        log.info("memberVO : " + memberVO);
        log.info("gender : " + gender);
    }

//    [실습1]
//    외부에서 상품명, 상품가격, 상품재고, 브랜드 전달받아서 화면에 전송
//    화면에서 4개 정보 입력 후 form태그로 전송한다.

    @GetMapping("/test1")
    public String product(){
        return "test1/test";
    }

    @PostMapping("/test1")
    public String product(ProductVO productVO){
        return "test1/result";
    }

//    [실습2]
//    TaskVO 선언
//    int num, int kor, int eng, int math 선언
//    총점과 평균 점수 화면에 출력

    @GetMapping("/test2")
    public String exam(){
        return "test2/test";
    }

    @PostMapping("/test2")
    public String exam(TaskVO taskVO){
        return "test2/result";
    }

//    [실습3]
//    아이디와 비밀번호를 입력받은 후 아이디가 admin일 경우 admin.html로 이동
//    아이디가 user일 경우 user.html로 이동
//    - login.html : 아이디와 비밀번호 입력 페이지 출력
//    - admin.html : 관리자 페이지 출력
//    - user.html : 일반 회원 페이지 출력

    @GetMapping("/test3")
    public String login(){
        return "test3/login";
    }

    @PostMapping("/test3")
    public String login(UserVO userVO) {
        return "login/" + (userVO.getId().equals("admin") ? "admin" : "user");
//        if(id.equals("admin")){
//            return "test3/admin";
//        }else if(id.equals("user")){
//            return "test3/user";
//        }else{
//            return null;
//        }
    }

//    [실습4]
//    이름을 입력하고 출근 또는 퇴근 버튼을 클릭한다.
//    출근 시간은 09:00이며, 퇴근 시간은 17:00이다.
//    출근 버튼 클릭 시 9시가 넘으면 지각으로 처리하고,
//    퇴근 버튼 클릭 시 17시 전이라면 퇴근이 아닌 업무시간으로 처리한다.
//    - getToWork.html
//    - leaveWork.html
//    - late.html
//    - work.html

    @GetMapping("/test4")
    public String goWork(){
        return "test4/getToWork";
    }

    @PostMapping("/test4")
    public String goWork(String id, String pw) {
        if(id.equals("admin")){
            return "test3/admin";
        }else if(id.equals("user")){
            return "test3/user";
        }else{
            return null;
        }
    }

}
