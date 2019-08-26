package com.acorn.acorngram.user.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.acorn.acorngram.user.dto.UserDto;
import com.acorn.acorngram.user.service.UserService;

@Controller
public class UserController {
	@Autowired UserService service;
	
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute UserDto dto, ModelAndView mView, HttpSession session, HttpServletRequest request) {
		service.validUser(dto, mView, session);
		
		String encodedUrl = URLEncoder.encode(request.getParameter("url"));
		request.setAttribute("encodedUrl", encodedUrl);
		
		mView.setViewName("user/login");
		return mView;
	}
	
	@RequestMapping("/user/signupform")
	public String signupform() {
		return "user/signupform";
	}
	
	@RequestMapping(value = "/user/signup", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute UserDto dto, ModelAndView mView) {
		service.signup(dto, mView);
		mView.addObject("dto", dto);
		mView.setViewName("users/signup");
		return mView;
	}
	
	@RequestMapping("/user/loginform")
	public String loginform(HttpServletRequest request) {
		String url = request.getParameter("url");
		
		if(url==null) {
			String cPath = request.getContextPath();
			url=cPath+"/";
		}
		
		request.setAttribute("url", url);
		
		return "users/loginform";
	}
	
	@RequestMapping("/user/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:home.do";
	}
	
	@RequestMapping("/user/info")
	public ModelAndView authInfo(HttpServletRequest request, ModelAndView mView) {
		service.showInfo(request.getSession(), mView);
		mView.setViewName("user/info");
		return mView;
	}
	
	@RequestMapping("/user/checkid")
	@ResponseBody
	public Map<String, Object> checkid(@RequestParam String inputId){
		Map<String, Object> map = service.isExist(inputId);
		return map;
	}
	
	@RequestMapping("/user/delete")
	public ModelAndView authDelete(HttpServletRequest request) {
		service.deleteUser(request.getSession());
		return new ModelAndView("redirect:/home.do");
	}
	
	@RequestMapping("/user/updateform")
	public ModelAndView authUpdateform(HttpServletRequest request, ModelAndView mView) {
		service.showInfo(request.getSession(), mView);
		mView.setViewName("user/updateform");
		return mView;
	}
	
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public ModelAndView authUpdateEmail(@ModelAttribute UserDto dto, ModelAndView mView, HttpServletRequest request) {
		boolean isUpdate = service.updateUser(dto, mView);
		
		mView.addObject("isUpdate", isUpdate);
		mView.setViewName("user/update");
		return mView;
	}
	
	@RequestMapping("/user/profile_upload")
	@ResponseBody
	public Map<String, Object> authProfileUpload(HttpServletRequest request, @RequestParam MultipartFile profileImage){
		String path = service.saveProfileImage(request, profileImage);
		Map<String, Object> map = new HashMap<>();
		map.put("path", path);
		return map;
	}
}
