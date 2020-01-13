package com.iu.b1.notice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.b1.Board.BoardVO;
import com.iu.b1.util.FilePathGe;
import com.iu.b1.util.FileSaver;

@Transactional
@Service
public class NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	@Autowired
	private FilePathGe filePathGe;
	@Autowired
	private FileSaver fileSaver;
	
	
	public NoticeVO noticeWrite(NoticeVO noticeVO, MultipartFile[] files) throws Exception{
		
		File file = filePathGe.getUseClassPathResource("board");
		List<NoticeFilesVO> list = new ArrayList<NoticeFilesVO>();
		for(MultipartFile mul : files) {
			if(mul !=null && !mul.getOriginalFilename().equals("")) {
				String fileName = fileSaver.save(mul, file);
				NoticeFilesVO noticeFilesVO = new NoticeFilesVO();
				noticeFilesVO.setFname(fileName);
				noticeFilesVO.setOname(mul.getOriginalFilename());
				noticeFilesVO.setNoticeVO(noticeVO);
				list.add(noticeFilesVO);
			}
		}
		noticeVO.setNoticeFilesVOs(list);
		
		
		return noticeRepository.save(noticeVO);
	}
	
	public Page<NoticeVO> noticeList(Pageable pageable, String kind, String search) throws Exception{
		
		if(kind == null) {
			kind = "";
		}
		
		switch (kind) {
		case "kt" :
				return noticeRepository.findByTitleContainingOrderByNumDesc(search, pageable);
		case "kw" :	
				return noticeRepository.findByWriterContainingOrderByNumDesc(search, pageable);
		case "kc" :
				return noticeRepository.findByContentsContainingOrderByNumDesc(search, pageable);
		default:
				return noticeRepository.findByNumGreaterThanOrderByNumDesc(0, pageable);

		}
		
	} 
	
	
	
	public Optional<NoticeVO> noticeSelect(int num) throws Exception{
		return noticeRepository.findById(num);
	}
}
