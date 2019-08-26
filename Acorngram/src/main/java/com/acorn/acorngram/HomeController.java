package com.acorn.acorngram;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @Controller 어노테이션
 *  
 *  - component scan 을 했을때 HomeController 객체가 생성이 되고 
 *    생성된 객체가 Spring Bean Container 에서 관리 되게 하는 역활
 *    
 *  - Spring MVC 프로젝트에서 Controller 가 되게 하는 역활
 */
@Controller
public class HomeController {
	// 최상위 "/" root 요청이 왔을때 응답할 메소드 
	@RequestMapping("/home.do")
	public String home(HttpServletRequest request) {//필요한 객체가 전달된다.
		// view page (jsp 페이지) 에 전달할 모델(데이터)
		List<String> noticeList=new ArrayList<String>();
		noticeList.add("여름이네요");
		noticeList.add("겔럭시 노트10 이 끝내줘요!");
		noticeList.add("어쩌구...");
		noticeList.add("저쩌구...");
		//HttpServletRequest 객체에 담는다.
		request.setAttribute("noticeList", noticeList);
		
		/*
		 *  "home" 을 리턴해주면 
		 *  
		 *  servlet-context.xml 문서의 설정 때문에 
		 *  
		 *  "/WEB-INF/views/" + "home" + ".jsp"
		 *  
		 *  "/WEB-INF/views/home.jsp" 가 응답할 jsp 페이지가 된다. 
		 *  
		 *  해당 jsp 페이지로 forward 이동 되어서 응답된다. 
		 */
		return "home"; 
	}
}











