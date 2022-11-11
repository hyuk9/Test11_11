package com.example.simpledms.repository;

import com.example.simpledms.model.Dept;
import com.example.simpledms.model.Emp;
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
 * fileName : EmpRepositoryTest
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
// 테스트 후 자동적으로 롤백됨( 내부적으로 @Transactional 이 붙어있음 )
@DataJpaTest
// 일반 DB 연결
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmpRepositoryTest {

    @Autowired
    private EmpRepository empRepository;

    @Test
    void findAllByEnameContaining() {

        Optional<Emp> emp = Optional.ofNullable(Emp.builder()
                .ename("홍길동")
                .job("SALES")
                .manager(8888)
//                .hiredate("1982-01-23 00:00:00")
//                .salary(1300)
                .build());

        // given : 예측 결과 저장
        Emp emp2 = empRepository.save(emp.get());


//       when : 실행
        List<Emp> list = empRepository.findAllByEnameContaining(emp2.getEname());

        // then
        assertThat(list.get(0).getEname()).isEqualTo(emp2.getEname());
    }

    @DisplayName("save() 저장 함수 ")
    @Test
    void save() {
        Optional<Emp> emp = Optional.ofNullable(Emp.builder()
                .ename("홍길동")
                .job("SALES")
                .manager(8888)
//                .hiredate("1982-01-23 00:00:00")
//                .salary(1300)
                .build());

        // when
        Emp emp2 = empRepository.save(emp.get());

        // then
        assertThat(emp2.getEname()).isEqualTo(emp.get().getEname());
    }

    @DisplayName("deleteAll() 모두 삭제 함수 ")
    @Test
    void deleteAll() {
        Optional<Emp> emp = Optional.ofNullable(Emp.builder()
                .ename("홍길동")
                .job("SALES")
                .manager(8888)
//                .hiredate("1982-01-23 00:00:00")
//                .salary(1300)
                .build());

        // given : 예측 결과 저장
        empRepository.save(emp.get());

        //       when : 실행
        empRepository.deleteAll();

        // then
        assertThat(empRepository.findAll()).isEqualTo(Collections.emptyList());
    }
}