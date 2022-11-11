package com.example.simpledms.controller;

import com.example.simpledms.model.Dept;
import com.example.simpledms.model.Emp;
import com.example.simpledms.service.DeptService;
import com.example.simpledms.service.EmpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName : com.example.simpledms.controller
 * fileName : EmpControllerTest
 * author : kangtaegyung
 * date : 2022/11/04
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/11/04         kangtaegyung          최초 생성
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmpController.class)
class EmpControllerTest {

//    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc; // 가짜 객체 받기
    @MockBean
    private EmpService empService;  // 가짜 객체를 서비스 객체에 넣기

    @Test
    void getEmpAll() throws Exception {
        //        테스트 값 설정
        List<Emp> list = new ArrayList<>();
        list.add(Emp.builder()
                .ename("홍길동")
                .job("SALES")
                .manager(7782)
//                .hiredate("1982-01-23 00:00:00")
//                .salary(1300)
                .build());

        list.add(Emp.builder()
                .ename("장길산")
                .job("SALES")
                .manager(7782)
                .hiredate("1982-01-23 00:00:00")
                .salary(1300)
                .build());

        //given
        given(empService.findAll())
                .willReturn(list);

        //when
        mockMvc.perform(get("/api/emp"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getEmpId() throws Exception {
        //        테스트 값 설정
        Optional<Emp> optionalEmp = Optional.ofNullable(Emp.builder()
                .ename("홍길동")
                .job("SALES")
                .manager(8888)
//                .hiredate("1982-01-23 00:00:00")
//                .salary(1300)
                .build());

        //given
        given(empService.findById(anyInt()))
                .willReturn(optionalEmp);

        //when
        mockMvc.perform(get("/api/emp/8888"))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.dname").value("SALES"))
                .andDo(print());
    }

    @Test
    void createEmp() throws Exception {
        Emp emp = Emp.builder()
                .ename("홍길동")
                .job("SALES")
                .manager(8888)
//                .hiredate("1982-01-23 00:00:00")
//                .salary(1300)
                .build();

        //given
        given(empService.save(any()))
                .willReturn(emp);

        //when
        mockMvc.perform(post("/api/emp")
                        .contentType(MediaType.APPLICATION_JSON)
//                모델 객체을 json 문자열 형태로 변환
                        .content(objectMapper.writeValueAsString(emp)))
//                        .content("{ \"dno\" : 10, \"dname\" : \"SALES\", \"loc\": \"SEOUL\"}"))
                .andExpect(status().isOk())
//                          .andExpect(jsonPath("$.dname").value("SALES"))
                .andDo(print());
    }

    @Test
    void updateEmp() throws Exception {
        Emp emp = Emp.builder()
                .ename("홍길동")
                .job("SALES")
                .manager(8888)
//                .hiredate("1982-01-23 00:00:00")
//                .salary(1300)
                .build();

        //given
        given(empService.save(any()))
                .willReturn(emp);

        //when
        mockMvc.perform(put("/api/emp/8888")
                        .contentType(MediaType.APPLICATION_JSON)
//                모델 객체을 json 문자열 형태로 변환
                        .content(objectMapper.writeValueAsString(emp)))
//                        .content("{ \"dno\" : 10, \"dname\" : \"SALES\", \"loc\": \"SEOUL\"}"))
                .andExpect(status().isOk())
//                          .andExpect(jsonPath("$.dname").value("SALES"))
                .andDo(print());
    }

    @Test
    void deleteEmp() throws Exception {
        //given
        given(empService.removeById(anyInt()))
                .willReturn(true);

        //when
        mockMvc.perform(delete("/api/emp/deletion/8888"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteAll() throws Exception {
        //given : void 함수
        willDoNothing().given(empService).removeAll();
        //when
        mockMvc.perform(delete("/api/emp/all"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}