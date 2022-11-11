package com.example.simpledms.controller;

import com.example.simpledms.model.Dept;
import com.example.simpledms.service.DeptService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * packageName : com.example.simpledms.controller
 * fileName : DeptControllerTest
 * author : kangtaegyung
 * date : 2022/10/31
 * description :
 * 요약 :
 * 통합 테스트가 아닌 서비스 로직에 대해서만 단위 테스트를 하기 위해서는
 * Repository 나 Controller 에 모두 독립적
 * @WebMvcTest(controllers = 대상_컨트롤러명.class) : 컨트롤러 테스트를 위한 어노테이션
 * Mockito : 개발자가 동작을 직접 제어할 수 있는 가짜(Mock) 객체를 지원하는 테스트 프레임워크
 * MockMvc : 가짜 객체 클래스
 * ObjectMapper : 잭슨 라이브러리 ( 객체 to Json, Json to 객체 )
 * 테스팅 방법 :
 *     1) 전제 (given) : 테스트 사전 조건이 주어짐, 결과로 예측될 값을 객체에 넣은 후 given 함수의 willReturn 값으로 지정
 *     2) 수행 (when, perform) : 실제 테스트가 진행됨, 테스팅 수행
 *          get(url)
 *          post(url).contentType(타입).content(객체)
 *          put(url).contentType(타입).content(객체)
 *          delete(url)
 *     3) 점검 (then, andExpect) : 테스트 결과를 알려줌, 테스팅 결과 점검
 *          status().isOK() : OK 상태메세지가 나오는가?
 *          jsonPath(json_객체_경로).value(값) : json 객체 경로에 그 값이 있는가?
 *          andDo(print()) : 현재 테스팅 결과를 화면에 출력, andDo(실행문) 함수
 *          그외 header(), cookie(), content(), view(), model() 등 많은 점검 대상 함수가 있음
 *     참고) jsonPath() : json 객체의 경로 탐색하는 라이브러리 함수
 *           $ : json 루트 경로
 *           .(닷) : 속성명 접근하는 접근자
 *           ex) {
 *                  "dname": "SALES",
 *                  "loc": "SEOUL"
 *               }
 *               => jsonPath($.dname).value("SALES") == "SALES"
 *
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/31         kangtaegyung          최초 생성
 */
//SpringContainer를 로드하므로 Test 객체에 @Autowired를 통해 Bean 의존성을 주입시킬 수 있다.
//        또한 Bean을 Mocking하기위한 @MockBean 기능을 사용할 수 있다.
//        테스트를 위해 Spring이 필요하다면 위 코드가 필요하다
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DeptController.class)
class DeptControllerTest {

//    private static final String MEMBER_NAME = "홍길동";
//    private static final Long MEMBER_ID = 1L;

    @Autowired
    private MockMvc mockMvc; // 가짜 객체 받기

    @MockBean
    private DeptService deptService;  // 가짜 객체를 서비스 객체에 넣기

//    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("getDeptAll() : 부서 모두 조회")
    @Test
    void getDeptAll() throws Exception {
//        테스트 값 설정
        List<Dept> list = new ArrayList<>();
        list.add(Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("Seoul")
                .build());
        list.add(Dept.builder()
                .dno(20)
                .dname("ACCOUNTING")
                .loc("Busan")
                .build());

        //given
        given(deptService.findAll())
                .willReturn(list);

        //when
        mockMvc.perform(get("/api/dept"))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.[0].dname").value("SALES"))
//                .andExpect(jsonPath("$.[1].dname").value("ACCOUNTING"))
                .andDo(print());

    }

    @DisplayName("getDeptId() : 부서 상세 조회")
    @Test
    void getDeptId() throws Exception {
//        테스트 값 설정
        Optional<Dept> optionalDept = Optional.ofNullable(Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("Seoul")
                .build());

        //given
        given(deptService.findById(anyInt()))
                .willReturn(optionalDept);

        //when
        mockMvc.perform(get("/api/dept/10"))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.dname").value("SALES"))
                .andDo(print());

    }

    @DisplayName("createDept() : 부서 추가")
    @Test
    void createDept() throws Exception {

        Dept dept = Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build();

        //given
        given(deptService.save(any()))
                .willReturn(dept);

        //when
        mockMvc.perform(post("/api/dept")
                        .contentType(MediaType.APPLICATION_JSON)
//                모델 객체을 json 문자열 형태로 변환
                        .content(objectMapper.writeValueAsString(dept)))
//                        .content("{ \"dno\" : 10, \"dname\" : \"SALES\", \"loc\": \"SEOUL\"}"))
                        .andExpect(status().isOk())
//                          .andExpect(jsonPath("$.dname").value("SALES"))
                        .andDo(print());

    }


    @DisplayName("updateDept() : 부서 수정")
    @Test
    void updateDept() throws Exception {

        Dept dept = Dept.builder()
                .dno(10)
                .dname("SALES2")
                .loc("SEOUL2")
                .build();

        //given
        given(deptService.save(any()))
                .willReturn(dept);

        //when
        mockMvc.perform(put("/api/dept/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dept)))
//                .content("{ \"dno\" : 10, \"dname\" : \"SALES2\", \"loc\": \"SEOUL2\"}"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.dname").value("SALES2"))
                .andDo(print());

    }

    @DisplayName("deleteDept() : 부서 삭제")
    @Test
    void deleteDept() throws Exception {

        //given
        given(deptService.removeById(anyInt()))
                .willReturn(true);

        //when
        mockMvc.perform(delete("/api/dept/deletion/10"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void deleteAll() throws Exception {
        //given : void 함수
        willDoNothing().given(deptService).removeAll();
        //when
        mockMvc.perform(delete("/api/dept/all"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}