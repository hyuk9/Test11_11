package com.example.simpledms.repository;

import com.example.simpledms.model.Dept;
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

/**
 * packageName : com.example.simpledms.repository
 * fileName : DeptRepositoryTest
 * author : kangtaegyung
 * date : 2022/11/01
 * description :
 * 요약 :
 *      @DataJpaTest : 테스트 후 자동 롤백 실행시키는 어노테이션으로 리파지토리 테스트에 사용함
 *      assertThat() : 결과 점검하는 함수, assertJ 라이브러리,
 *          다양한 체이닝 함수가 있음 : .isEqualTo(), isGreatThan(), isLessThan() 등
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/11/01         kangtaegyung          최초 생성
 */
@ExtendWith(SpringExtension.class)
// 테스트 후 자동적으로 롤백됨( 내부적으로 @Transactional 이 붙어있음 )
@DataJpaTest
// 일반 DB 연결
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// 기본적으로 내장 DB 연결 ( 인 메모리 DB )
class DeptRepositoryTest {

    @Autowired
    private DeptRepository deptRepository;

    @DisplayName("findAllByDnameContaining() 서비스 조회 함수 ")
    @Test
    void findAllByDnameContaining() {

        Optional<Dept> dept = Optional.ofNullable(Dept.builder()
                .dname("SALES")
                .loc("SEOUL")
                .build());

        // when
        Dept dept2 = deptRepository.save(dept.get());
        List<Dept> list = deptRepository.findAllByDnameContaining("SALES");

        // then
        assertThat(list.get(0).getDname()).isEqualTo("SALES");

    }

    @DisplayName("save() 저장 함수 ")
    @Test
    void save() {
        Optional<Dept> dept = Optional.ofNullable(Dept.builder()
                .dname("SALES")
                .loc("SEOUL")
                .build());

        // when
        Dept dept2 = deptRepository.save(dept.get());

        // then
        assertThat(dept2.getDname()).isEqualTo("SALES");
    }

    @DisplayName("deleteAll() 모두 삭제 함수 ")
    @Test
    void deleteAll() {
        Optional<Dept> dept = Optional.ofNullable(Dept.builder()
                .dname("SALES")
                .loc("SEOUL")
                .build());

        // when
        deptRepository.save(dept.get());
        deptRepository.deleteAll();

        // then
        assertThat(deptRepository.findAll()).isEqualTo(Collections.emptyList());
    }
}