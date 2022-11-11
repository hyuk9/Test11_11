package com.example.simpledms.service;

import com.example.simpledms.model.Emp;
import com.example.simpledms.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.modelexam.service
 * fileName : EmpService
 * author : kangtaegyung
 * date : 2022/10/12
 * description : 사원 업무 서비스 클래스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/12         kangtaegyung          최초 생성
 */
// springboot 프레임워크에 객체를 생성함 : 싱글톤 유형
@Service
public class EmpService {

    @Autowired
    EmpRepository empRepository; // 샘플데이터 DB에 접근하는 객체

    public List<Emp> findAll() {
        List<Emp> list = empRepository.findAll();

        return list;
    }

    public Optional<Emp> findById(int eno) {
        Optional<Emp> optionalEmp = empRepository.findById(eno);

        return optionalEmp;
    }

    public Emp save(Emp emp) {

        Emp emp2 = empRepository.save(emp);

        return emp2;
    }

    public boolean removeById(int id) {

        if (empRepository.existsById(id)) {
            empRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void removeAll() {

        empRepository.deleteAll();
    }

//    dname like 검색
    public List<Emp> findAllByEnameContaining(String ename) {

        List<Emp> emp = empRepository.findAllByEnameContaining(ename);

        return emp;
    }

}






















