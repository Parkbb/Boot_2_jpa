package com.iu.b1.qna;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.iu.b1.Board.BoardVO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "qna")
@Data
public class QnaVO extends BoardVO{
	
	private Integer ref;
	private Integer step;
	private Integer depth;
	
	@OneToMany(mappedBy = "qnaVO", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<QnaFilesVO> qnaFilesVOs;
	
}
