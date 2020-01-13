package com.iu.b1.qna;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface QnaRepository extends JpaRepository<QnaVO, Integer>{
	
	
	public Page<QnaVO> findByNumGreaterThanOrderByRefDescStepAsc(Integer num,Pageable pageable) throws Exception;
	
	@Query("select q from QnaVO q order by q.ref desc, q.step asc")
	List<QnaVO> findByNum() throws Exception;
	
	@Query("select q from QnaVO q where num= ?1")
	QnaVO findQnaVO(int num) throws Exception;
	
	@Query("select q from QnaVO q where num= :num")
	QnaVO findQnaVOs(@Param("num") int num) throws Exception;
	
	@Modifying
	@Query("update QnaVO q set q.title= :title where q.num=:num")
	int updateQnaT(@Param("title") String title, @Param("num") int num) throws Exception;
	
	@Query("select q.writer, q.contents from QnaVO q where num=?1")
	List<Object[]> qnaSelect(int num) throws Exception;
}
