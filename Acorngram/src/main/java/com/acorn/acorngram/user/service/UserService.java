package com.acorn.acorngram.user.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.acorn.acorngram.user.dto.UserDto;

public interface UserService {
	public void signup(UserDto dto, ModelAndView mView);
	public String getPwdHash(ModelAndView mView, String id);
	public UserDto getData(UserDto dto);
	public void deleteUser(HttpSession session);
	public void validUser(UserDto dto, ModelAndView mView, HttpSession session);
	public void showInfo(HttpSession session, ModelAndView mView);
	public Map<String, Object> isExist(String inputId); //id를 던저주면 Map을 리턴받는다.
	//개인정보 수정 반영하는 메소드
	public boolean updateUser(UserDto dto, ModelAndView mView);
	//프로파일 이미지를 저장하는 메소드
	public String saveProfileImage(HttpServletRequest request, MultipartFile mFile);
}
