package com.iu.b1.notice;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.b1.Board.BoardVO;

@Controller
@RequestMapping("notice/**")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute(name = "board")
	public String getBoard() {
		return "notice";
	}
	
	//noticecontroller내부 모든 메서드 실행 전에 먼저 실행
	@ModelAttribute(name = "noticeVO")
	public NoticeVO getNoticeVO() {
		return new NoticeVO();
	}
	
	@GetMapping("noticeWrite")
	public void noticeWrite() throws Exception{
		
	}
	
	@PostMapping("noticeWrite")
	public ModelAndView noticeWrite(@Valid NoticeVO noticeVO, BindingResult bindingResult,MultipartFile[] files, ModelAndView mv) throws Exception{
		if(bindingResult.hasErrors()) {
			mv.addObject("noticeVO", noticeVO);
			mv.setViewName("notice/noticeWrite");
		}else {
			noticeVO = noticeService.noticeWrite(noticeVO, files);
			mv.addObject("message", "공지 작성 성공");
			mv.addObject("path", "./noticeList");
			mv.setViewName("common/commonResult");
		}
		return mv;
	}
	
	@GetMapping("noticeList")
	public ModelAndView noticeList(ModelAndView mv,@PageableDefault(page = 0,size = 10, sort = "num",direction = Direction.DESC) Pageable pageable,
			String kind, String search) throws Exception{
		Page<NoticeVO> ar = noticeService.noticeList(pageable,kind,search);
		
		//정렬 넣을거면 메서드에 정렬기준 없애기
		//Pageable pageable2 = PageRequest.of(0, 10, Sort.Direction.ASC,"num");
		int curBlock =1 + (ar.getNumber()/5 -(ar.getNumber()%5/5));
		int totalBlock =1 + (ar.getTotalPages()/5-(ar.getTotalPages()%5/5));
		mv.addObject("totalBlock", totalBlock);
		mv.addObject("curBlock", curBlock);
		mv.addObject("list", ar);
		//mv.addObject("kind", kind);
		return mv;
	}
	
	@GetMapping("noticeSelect")
	public ModelAndView noticeSelect(ModelAndView mv, Integer num) throws Exception{
		Optional<NoticeVO> opt = noticeService.noticeSelect(num);
		
		if(opt.isPresent()) {
			mv.addObject("noticeVO", opt.get());
			mv.setViewName("notice/noticeSelect");
		}else {
			mv.addObject("message", "존재하지 않는 글입니다.");
			mv.addObject("path", "./noticeList");
			mv.setViewName("common/commonResult");
		}
		return mv;
	}
}
