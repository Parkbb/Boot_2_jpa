package com.iu.b1.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
class QnaRepoTest {

	@Resource
	private QnaRepository qnaRepository;

	//@Test
	void test() throws Exception{
//		List<QnaFilesVO> list = new ArrayList<QnaFilesVO>();
//		QnaVO qnaVO = new QnaVO();
//		qnaVO.setTitle("hello");
//		qnaVO.setContents("hello");
//		qnaVO.setWriter("hello");
//		for (int i = 0; i < 2; i++) {
//			QnaFilesVO qnaFilesVO = new QnaFilesVO();
//			qnaFilesVO.setFname(i+"hello.jpg");
//			qnaFilesVO.setOname(i+"hello.jpg");
//			qnaFilesVO.setQnaVO(qnaVO);
//			list.add(qnaFilesVO);
//		}
//		qnaVO.setQnaFilesVOs(list);
//		qnaRepository.save(qnaVO);
		
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<QnaVO> p = qnaRepository.findByNumGreaterThanOrderByRefDescStepAsc(0, pageable);
		System.out.println(p.getSize());
		System.out.println(p.getTotalPages());
		System.out.println(p.getContent().size());
	}
	@Test
	@Rollback(false)
	void test2() throws Exception{
		
//		List<QnaVO> ar = qnaRepository.findByNum();
//		System.out.println(ar.size());
//		for(QnaVO qnaVO : ar) {
//			System.out.println(qnaVO.getTitle());
//			for(QnaFilesVO qnaFilesVO : qnaVO.getQnaFilesVOs()) {
//				System.out.println(qnaFilesVO.getFname());
//			}
//		}
		
		
//		QnaVO qnavo = qnaRepository.findQnaVO(2);
//		QnaVO qna = qnaRepository.findQnaVOs(1);
//		System.out.println(qnavo.getTitle());
//		System.out.println(qna.getTitle());
		
		//qnaRepository.updateQnaT("retitle", 1);
		
		List<Object[]> list = qnaRepository.qnaSelect(1);
		
		
	}

}
