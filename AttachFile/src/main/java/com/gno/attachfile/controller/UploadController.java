package com.gno.attachfile.controller;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller		// 스프링에 컨트롤러로 인식
public class UploadController {
	
	// ---------- Logger 객체 생성 ----------
	private static final Logger logger 
				= LoggerFactory.getLogger(UploadController.class);
	
	// ---------- 파일 저장 경로 설정 ----------
	/*
	 * uploadpath → c:\\uploadfiles
	 */
	@Resource(name = "uploadpath")
	private String uploadpath;
	
	// ---------- 파일 업로드 ---------- [ 1 ]
	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public void uploadFormGET() {
		
	}
	
	// ---------- 파일 업로드 ---------- [ 2 ]
	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public void uploadFormPOST(
			MultipartFile file, Model model) throws Exception {
			// 선택한 파일 객체의 정보가 자동으로 들어온다.
		
		logger.info("원래 파일 이름 : " + file.getOriginalFilename());
		logger.info("파일크기(Byte) : " + file.getSize());
		logger.info("컨텐츠 타입 : " + file.getContentType());
		logger.info("파일 이름 : " + file.getName());
		
		String savedName = uploadFile(
						file.getOriginalFilename(), file.getBytes());
		
		model.addAttribute("savedName", savedName);
	}
	
	// ---------- 파일 업로드 ---------- [ 3 ]
	/*
	 * 파일의 원본 이름과, 바이트로 변환한 파일
	 */
	private String uploadFile(
			String originalName, byte[] fileData) throws Exception {
		
		UUID uid = UUID.randomUUID();	
					// 업로드되는 파일에 중복되지 않는 고유한 (String Type)이름을 부여한다.
		
		String savedName = uid.toString() + "_" + originalName;
					// 고유한 이름이 부여된 업로드되는 파일에 "_"를 이용해서 원본 파일 이름을 연결한다.
		
		File target = new File(uploadpath, savedName);
					// 해당하는 경로에 해당 파일을 업로드하겠다는 설정이다.
		
		FileCopyUtils.copy(fileData, target);
					// 해당하는 경로에 해당 파일을 복사해서 넣는다(전송한다).
		
		return savedName;
	}

}
