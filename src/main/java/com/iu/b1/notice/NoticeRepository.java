package com.iu.b1.notice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iu.b1.Board.BoardVO;

public interface NoticeRepository extends JpaRepository<NoticeVO, Integer>{
	
	public Page<NoticeVO> findByNumGreaterThanOrderByNumDesc(Integer num,Pageable pageable) throws Exception;
	
	public Page<NoticeVO> findByTitleContainingOrderByNumDesc(String search, Pageable pageable) throws Exception;
	
	public Page<NoticeVO> findByWriterContainingOrderByNumDesc(String search, Pageable pageable) throws Exception;
	
	public Page<NoticeVO> findByContentsContainingOrderByNumDesc(String search, Pageable pageable) throws Exception;
}
