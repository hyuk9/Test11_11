package com.example.simpledms.service;

import com.example.simpledms.model.Dept;
import com.example.simpledms.repository.DeptRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * packageName : com.example.simpledms.service
 * fileName : DeptServiceTest
 * author : kangtaegyung
 * date : 2022/10/31
 * description :
 * 요약 :
 *  가짜객체 :  통합 테스트가 아닌 서비스 로직에 대해서만 단위 테스트를 하기 위해서는
 *  * Repository 나 Controller 에 모두 독립적이어야 함
 *         Given(테스트 사전 조건이 주어짐),
 *         When(실제 테스트가 진행됨),
 *         Then(테스트 결과를 알려줌, assertThat()) 순서로 나는 간단하게 이해
 *         verify(리포지토리객체, times(실행횟수)).함수() :   싫행이 몇번 되는지 검증
 *  *      assertThat().isEqualTo(결과예측) : 결과 점검하는 함수, assertJ 라이브러리,
 *  *          다양한 체이닝 함수가 있음 : .isEqualTo(), isGreatThan(), isLessThan() 등
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/31         kangtaegyung          최초 생성
 */
//SpringContainer를 로드하지않고 테스트를 위한 기능만 제공한다.
//@Mock, @Spy 기능을 사용할 수 있다.
//        테스트에 Spring이 필요없이 순수한 단위 테스트만 필요하다면 위 코드를 추가하면 된다.
@Slf4j
@ExtendWith(MockitoExtension.class)
class DeptServiceTest {

    @Mock
    private DeptRepository deptRepository; // 가짜 객체 생성

    @InjectMocks
    private DeptService deptService; // 가짜 객체를 가져오기

    @DisplayName("findAll() 서비스 조회 함수 ")
    @Test
    void findAll() {
        // given
        List<Dept> list = new ArrayList<>();
        list.add(Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build());

        list.add(Dept.builder()
                .dno(20)
                .dname("ACCOUNTING")
                .loc("DAEGU")
                .build());

        given(deptRepository.findAll()).willReturn(list);

        List<Dept> list2 = deptService.findAll();

        assertThat(list2.get(0).getDname()).isEqualTo("SALES");
        assertThat(list2.get(1).getDname()).isEqualTo("ACCOUNTING");
    }

    @DisplayName("findById() 서비스 조회 함수 ")
    @Test
    void findById() {
        // given
        Optional<Dept> dept = Optional.ofNullable(Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build());

        given(deptRepository.findById(anyInt())).willReturn(dept);

        Optional<Dept> optionalDept = deptService.findById(anyInt());

//        given 에서 willReture 매개변수로 사용한 객체는 모두 통과됨 ( 사용할 수 없음 )
        assertThat(optionalDept.get().getDname()).isEqualTo("SALES");
//        assertThat(optionalDept.get().getDname()).isEqualTo("SALES_해킹");
    }

    @DisplayName("save() 서비스 저장 함수 ")
    @Test
    void save() {
        Dept dept = Dept.builder().dno(10).dname("SALES").loc("SEOUL").build();

//        given
        given(deptRepository.save(any())).willReturn(dept);
//        when
        Dept dept2 = deptService.save(dept);

//        then
        assertThat(dept2.getDname()).isEqualTo("SALES");
    }

    @DisplayName("removeById() 서비스 저장 함수 ")
    @Test
    void removeById() {

//        given : 가정, 전제
        given(deptRepository.existsById(anyInt())).willReturn(true);

//        when : 실제 수행
        boolean bSuccessed = deptService.removeById(anyInt());

//         싫행 되었는 지 검증
        verify(deptRepository, times(1)).deleteById(anyInt());
        assertThat(bSuccessed).isEqualTo(true);  // true 인지 확인
    }

    @DisplayName("removeAll() 서비스 모두 삭제 함수 ")
    @Test
    void removeAll() {
//        when : 실제 수행
        deptService.removeAll();

//         싫행 되었는 지 검증
        verify(deptRepository, times(1)).deleteAll();
    }

    @DisplayName("findAllByDnameContaining() 서비스 부서명으로 검색 함수 ")
    @Test
    void findAllByDnameContaining() {
        // given
        List<Dept> list = new ArrayList<>();
        list.add(Dept.builder().dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build());

        list.add(Dept.builder().dno(20)
                .dname("ACCOUNTING")
                .loc("DAEGU")
                .build());

        //        given : 가정, 전제
        given(deptRepository.findAllByDnameContaining(any())).willReturn(list);

        //        when : 실제 수행
        List<Dept> list2 = deptService.findAllByDnameContaining(any());

        //         싫행 되었는 지 검증
        assertThat(list2.get(0).getDname()).isEqualTo("SALES");
        assertThat(list2.get(1).getDname()).isEqualTo("ACCOUNTING");
    }
}