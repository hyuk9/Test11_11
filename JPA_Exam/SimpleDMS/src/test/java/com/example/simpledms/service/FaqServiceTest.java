package com.example.simpledms.service;

import com.example.simpledms.model.Faq;
import com.example.simpledms.repository.FaqRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * packageName : com.example.simpledms.service
 * fileName : FaqServiceTest
 * author : hyuk
 * date : 2022/11/11
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022/11/11         hyuk          최초 생성
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
class FaqServiceTest {

    @Mock
    private FaqRepository faqRepository;

    @InjectMocks
    private FaqService faqService;

    @DisplayName("findAll() 서비스 조회 함수")
    @Test
    void findAll() {
        List<Faq> list = new ArrayList<>();
        list.add(Faq.builder()
                .title("제목1")
                .content("내용1")
                .build());

        given(faqRepository.findAll()).willReturn(list);

        List<Faq> list2 = faqRepository.findAll();

        assertThat(list2.get(0).getTitle()).isEqualTo("제목1");
    }

    @DisplayName("findById() 서비스 조회 함수")
    @Test
    void findById() {
        Optional<Faq> faq = Optional.ofNullable(Faq.builder()
                .title("제목1")
                .content("내용1")
                .build());

        given(faqRepository.findById(anyInt())).willReturn(faq);

        Optional<Faq> optionalFaq = faqService.findById(anyInt());

        assertThat(optionalFaq.get().getTitle()).isEqualTo("제목1");
    }

    @DisplayName("save() 서비스 저장 함수")
    @Test
    void save() {
        Faq faq = Faq.builder().title("제목1").content("내용1").build();

        given(faqRepository.save(any())).willReturn(faq);

        Faq faq2 = faqService.save(faq);

        assertThat(faq2.getTitle()).isEqualTo("제목1");
    }

    @DisplayName("removeById() 서비스 저장 함수")
    @Test
    void removeById() {
        given(faqRepository.existsById(anyInt())).willReturn(true);

        boolean bSuccessed = faqService.removeById(anyInt());

        verify(faqRepository, times(1)).deleteById(anyInt());
        assertThat(bSuccessed).isEqualTo(true);
    }

    @DisplayName("removeAll() 서비스 모두 삭제 함수 ")
    @Test
    void removeAll() {
        faqService.removeAll();

        verify(faqRepository, times(1)).deleteAll();
    }

    @DisplayName("findAllByTitleContaining() 서비스 부서명으로 검색 함수 ")
    @Test
    void findAllByTitleContaining() {
        List<Faq> list = new ArrayList<>();
        list.add(Faq.builder().title("제목1").content("내용1").build());

        given(faqRepository.findAllByTitleContaining(any())).willReturn(list);

        List<Faq> list2 = faqService.findAllByTitleContaining(any());

        assertThat(list2.get(0).getTitle()).isEqualTo("제목1");
    }
}