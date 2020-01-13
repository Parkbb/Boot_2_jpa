package com.iu.b1.qna;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class QnaService {
	
	@Resource
	private QnaRepository qnaRepository;
	
	
	public int qnaUpdate(QnaVO qnaVO) throws Exception{
		qnaVO.setTitle("변해라");
		qnaVO.setNum(1);
		return qnaRepository.updateQnaT(qnaVO.getTitle(), qnaVO.getNum());
	}
}
