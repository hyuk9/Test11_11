package com.example.simpledms.controller;

import com.example.simpledms.model.Faq;
import com.example.simpledms.service.FaqService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName : com.example.simpledms.controller
 * fileName : FaqControllerTest
 * author : hyuk
 * date : 2022/11/11
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022/11/11         hyuk          최초 생성
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FaqController.class)
class FaqControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FaqService faqService;

    ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("getFaqAll() : faq 모두 조회")
    @Test
    void getFaqAll() throws Exception{
        List<Faq> list = new ArrayList<>();
        list.add(Faq.builder()
                .title("제목1")
                .content("내용1")
                .build());

        given(faqService.findAll())
                .willReturn(list);

        mockMvc.perform(get("/api/faq"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @DisplayName("getFaqId() : faq 상세 조회")
    @Test
    void getFaqId() throws Exception{
        Optional<Faq> optionalFaq = Optional.ofNullable(Faq.builder()
                .title("제목1")
                .content("내용1")
                .build());

        given(faqService.findById(anyInt()))
                .willReturn(optionalFaq);

        mockMvc.perform(get("/api/faq/1"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @DisplayName("createFaq() : faq 추가")
    @Test
    void createFaq() throws Exception{
        Faq faq = Faq.builder().title("제목1").content("내용1").build();

        given(faqService.save(any()))
                .willReturn(faq);

        mockMvc.perform(post("/api/faq")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faq)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @DisplayName("updateFaq() : faq 수정")
    @Test
    void updateFaq() throws Exception{
        Faq faq = Faq.builder().title("제목1").content("내용1").build();

        given(faqService.save(any()))
                .willReturn(faq);

        mockMvc.perform(put("/api/faq/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faq)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @DisplayName("deleteFaq() : faq 삭제")
    @Test
    void deleteFaq() throws Exception{
        given(faqService.removeById(anyInt()))
                .willReturn(true);

        mockMvc.perform(delete("/api/faq/deletion/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @DisplayName("deleteAll() : faq 모두 삭제")
    @Test
    void deleteAll() throws Exception{
        willDoNothing().given(faqService).removeAll();

        mockMvc.perform(delete("/api/faq/all"))
                .andExpect(status().isOk())
                .andDo(print());

    }
}