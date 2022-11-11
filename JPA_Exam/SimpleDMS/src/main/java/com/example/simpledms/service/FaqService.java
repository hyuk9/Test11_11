package com.example.simpledms.service;

import com.example.simpledms.model.Dept;
import com.example.simpledms.model.Faq;
import com.example.simpledms.repository.DeptRepository;
import com.example.simpledms.repository.FaqRepository;
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
public class FaqService {

    @Autowired
    FaqRepository faqRepository; // 샘플데이터 DB에 접근하는 객체

    public List<Faq> findAll() {
        List<Faq> list = faqRepository.findAll();

        return list;
    }

    public Optional<Faq> findById(int dno) {
        Optional<Faq> optionalFaq = faqRepository.findById(dno);

        return optionalFaq;
    }

    public Faq save(Faq faq) {

        Faq faq2 = faqRepository.save(faq);

        return faq2;
    }

    public boolean removeById(int id) {

        if (faqRepository.existsById(id)) {
            faqRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void removeAll() {

        faqRepository.deleteAll();
    }

    //    dname like 검색
    public List<Faq> findAllByTitleContaining(String dname) {

        List<Faq> faq = faqRepository.findAllByTitleContaining(dname);

        return faq;
    }

}






















