package com.example.simpledms.service;

import com.example.simpledms.model.Dept;
import com.example.simpledms.model.Emp;
import com.example.simpledms.repository.DeptRepository;
import com.example.simpledms.repository.EmpRepository;
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
 * fileName : EmpServiceTest
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
@ExtendWith(MockitoExtension.class)
class EmpServiceTest {

    @Mock
    private EmpRepository empRepository; // 가짜 객체 생성

    @InjectMocks
    private EmpService empService; // 가짜 객체를 가져오기

    @Test
    void findAll() {
        // given
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

        given(empRepository.findAll()).willReturn(list);

        List<Emp> list2 = empService.findAll();

        assertThat(list2.get(0).getEname()).isEqualTo("홍길동");
        assertThat(list2.get(1).getEname()).isEqualTo("장길산");
    }

    @Test
    void findById() {
        // given
        Optional<Emp> emp = Optional.ofNullable(Emp.builder()
                .ename("장길산")
                .job("SALES")
                .manager(7782)
                .hiredate("1982-01-23 00:00:00")
                .salary(1300)
                .build());

        given(empRepository.findById(anyInt())).willReturn(emp);

        Optional<Emp> optionalEmp = empService.findById(anyInt());

        assertThat(optionalEmp.get().getEname()).isEqualTo("홍길동");
    }

    @Test
    void save() {
        Emp emp = Emp.builder()
                .ename("장길산")
                .job("SALES")
                .manager(7782)
                .hiredate("1982-01-23 00:00:00")
                .salary(1300)
                .build();

//        given
        given(empRepository.save(any())).willReturn(emp);
//        when
        Emp emp2 = empService.save(emp);

//        then
        assertThat(emp2.getEname()).isEqualTo("장길산");
    }

    @Test
    void removeById() {

//        given : 가정, 전제
        given(empRepository.existsById(anyInt())).willReturn(true);

//        when : 실제 수행
        boolean bSuccessed = empService.removeById(anyInt());

//      verify :   싫행이 몇번 되는지 검증
        verify(empRepository, times(1)).deleteById(anyInt());
        assertThat(bSuccessed).isEqualTo(true);  // true 인지 확인
    }

    @Test
    void removeAll() {
        //        when : 실제 수행
        empService.removeAll();

//         싫행 되었는 지 검증
        verify(empRepository, times(1)).deleteAll();
    }

    @Test
    void findAllByEnameContaining() {
        // given
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

        //        given : 가정, 전제
        given(empRepository.findAllByEnameContaining(any())).willReturn(list);

        //        when : 실제 수행
        List<Emp> list2 = empService.findAllByEnameContaining(any());

        //         싫행 되었는 지 검증
        assertThat(list2.get(0).getEname()).isEqualTo("홍길동");
        assertThat(list2.get(1).getEname()).isEqualTo("장길산");
    }
}