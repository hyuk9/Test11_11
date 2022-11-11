package com.example.simpledms.service;

import com.example.simpledms.model.Dept;
import com.example.simpledms.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.modelexam.service
 * fileName : DeptService
 * author : kangtaegyung
 * date : 2022/10/12
 * description : 부서 업무 서비스 클래스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/12         kangtaegyung          최초 생성
 */
// springboot 프레임워크에 객체를 생성함 : 싱글톤 유형
@Service
public class DeptService {

    @Autowired
    DeptRepository deptRepository; // 샘플데이터 DB에 접근하는 객체

    public List<Dept> findAll() {
        List<Dept> list = deptRepository.findAll();

        return list;
    }

    public Optional<Dept> findById(int dno) {
        Optional<Dept> optionalDept = deptRepository.findById(dno);

        Dept dept = optionalDept.get();

//      1)  데이터 가공 : 원본 Dname + "_해킹" 붙임
        dept.setDname(dept.getDname() + "_해킹");

//      2) 다시 옵셔널 객체에 넣기
        optionalDept = Optional.ofNullable(dept);

        return optionalDept;
    }

    public Dept save(Dept dept) {

        Dept dept2 = deptRepository.save(dept);

        return dept2;
    }

    public boolean removeById(int id) {

        if (deptRepository.existsById(id)) {
            deptRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void removeAll() {

        deptRepository.deleteAll();
    }

    //    dname like 검색
    public List<Dept> findAllByDnameContaining(String dname) {

        List<Dept> list = deptRepository.findAllByDnameContaining(dname);

        return list;
    }

}






















