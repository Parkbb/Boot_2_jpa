package com.iu.b1.member;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.b1.util.FilePathGe;
import com.iu.b1.util.FileSaver;

@Service
@Transactional
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MemberFilesRepository memberFilesRepository;
	@Autowired
	private FileSaver fileSaver;
	@Autowired
	private FilePathGe filePathGe;
	
	
	public List<MemberVO> memberLogin(MemberVO memberVO) throws Exception{
		return memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());
	}
	
	public List<MemberFilesVO> Mypage(MemberVO memberVO) throws Exception{
		return memberFilesRepository.findByIdEquals(memberVO.getId());
	}
	
	public MemberVO memberJoin(MemberVO memberVO, MultipartFile files) throws Exception{
		File file = filePathGe.getUseClassPathResource("upload");
		String fileName = fileSaver.save(files, file);
		memberVO = memberRepository.save(memberVO);
		MemberFilesVO memberFilesVO = new MemberFilesVO();
		memberFilesVO.setId(memberVO.getId());
		memberFilesVO.setFname(fileName);
		memberFilesVO.setOname(files.getOriginalFilename());
		memberFilesRepository.save(memberFilesVO);
		
		return memberVO;
	}
}
