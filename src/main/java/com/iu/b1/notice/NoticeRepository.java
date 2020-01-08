package com.iu.b1.notice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeVO, Integer>{
	
	public List<NoticeVO> findByNumGreaterThanOrderByNumDesc(Integer num) throws Exception;
	
}
