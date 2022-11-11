package com.example.simpledms.repository;

import com.example.simpledms.model.Faq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName : com.example.simpledms.repository
 * fileName : FaqRepositoryTest
 * author : hyuk
 * date : 2022/11/11
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022/11/11         hyuk          최초 생성
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FaqRepositoryTest {

    @Autowired
    private FaqRepository faqRepository;

    @DisplayName("findAllByTitleContaining() 서비스 조회 함수")
    @Test
    void findAllByTitleContaining() {
        Optional<Faq> faq = Optional.ofNullable(Faq.builder()
                .title("제목1")
                .content("내용1")
                .build());

        Faq faq2 = faqRepository.save(faq.get());
        List<Faq> list = faqRepository.findAllByTitleContaining("제목1");

        assertThat(list.get(0).getTitle()).isEqualTo("제목1");
    }

    @DisplayName("save() 저장 함수")
    @Test
    void save() {
        Optional<Faq> faq = Optional.ofNullable(Faq.builder()
                .title("제목1")
                .content("내용1")
                .build());

        Faq faq2 = faqRepository.save(faq.get());

        assertThat(faq2.getTitle()).isEqualTo("제목1");
    }

    @DisplayName("delete() 삭제 함수")
    @Test
    void delete() {
        Optional<Faq> faq = Optional.ofNullable(Faq.builder()
                .title("제목1")
                .content("내용1")
                .build());

        faqRepository.save(faq.get());
        faqRepository.deleteAll();

        assertThat(faqRepository.findAll()).isEqualTo(Collections.emptyList());
    }
}