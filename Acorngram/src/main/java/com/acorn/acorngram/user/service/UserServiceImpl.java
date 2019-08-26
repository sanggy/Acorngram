package com.acorn.acorngram.user.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.acorn.acorngram.user.dao.UserDao;
import com.acorn.acorngram.user.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;
	
	@Override
	public void signup(UserDto dto, ModelAndView mView) {
		//암호화 된 비밀번호를 UsersDto객체에 담고
		String rawPwd = dto.getPassword();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		dto.setPassword(encoder.encode(rawPwd));
		
		//Dao를 이용해서 DB에 저장한다.
		dao.signUp(dto);
		mView.addObject("id", dto.getId());
	}

	@Override
	public String getPwdHash(ModelAndView mView, String id) {
		String pwd = dao.getPwdHash(id);
		mView.addObject("pwd", pwd);
		return pwd;
	}

	@Override
	public UserDto getData(UserDto dto) {
		
		return dao.getData(dto.getId());
	}

	@Override
	public void deleteUser(HttpSession session) {
		//세션에서 로그인된 아이디 읽어와서 DB에서 삭제하기
		dao.delete((String)session.getAttribute("id"));
		//로그아웃 처리 하기 -> 탈퇴하면서
		session.invalidate();
	}

	@Override
	public void validUser(UserDto dto, ModelAndView mView, HttpSession session) {
		//아이디 비밀번호가 유효한 정보인지 여부
		boolean isValid = false;
		
		String pwdHash = dao.getPwdHash(dto.getId());
		//만일 아이디에 해당하는 비밀번호가 존재한다면
		if(pwdHash != null) {
			//비번 일치 여부를 얻어낸다.
			isValid = BCrypt.checkpw(dto.getPassword(), pwdHash);
		}
		
		if(isValid) {
			//로그인 처리를 한다.
			session.setAttribute("id", dto.getId());
			mView.addObject("isSuccessful", true);
		}else {
			mView.addObject("isSuccessful", false);
		}
	}

	@Override
	public void showInfo(HttpSession session, ModelAndView mView) {
		
		//세션에 저장된 아이디를 읽어와서
		String id = (String) session.getAttribute("id");
		//개인 정보를 읽어오고
		UserDto dto = dao.getData(id);
		//request 영역에 담기도록 ModelAndView 객체에 담아준다.
		mView.addObject("dto", dto);
		
	}

	@Override
	public Map<String, Object> isExist(String inputId) {
		boolean isExist = dao.isExist(inputId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("isExist", isExist);
		
		return map;
	}
	
	@Override
	public boolean updateUser(UserDto dto, ModelAndView mView) {
		System.out.println("service 부문 : " + dto.getPassword() + " : " + dto.getEmail());
		//암호화 된 비밀번호를 UsersDto객체에 담고
		if(dto.getPassword() != null && dto.getEmail() == null) {
			String rawPwd = dto.getPassword();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			dto.setPassword(encoder.encode(rawPwd));
			System.out.println("Service 부분 : " + dto.getId() + " : " + dto.getPassword());
			return dao.updateUserPwd(dto);
		}
		else if(dto.getPassword() == null) {
			return dao.updateUserEmail(dto);
		}
		else {
			String rawPwd = dto.getPassword();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			dto.setPassword(encoder.encode(rawPwd));
			System.out.println("Service 부분 : " + dto.getId() + " : " + dto.getPassword());
			return dao.updateUser(dto);
		}
	}

	@Override
	public String saveProfileImage(HttpServletRequest request, MultipartFile mFile) {
		
		//파일을 저장할 폴더의 절대 경로를 얻어온다.
		String realPath=request.getSession().getServletContext().getRealPath("/upload");
		//원본 파일명
		String orgFileName=mFile.getOriginalFilename();
		//저장할 파일의 상세 경로
		String filePath=realPath+File.separator;
		//디렉토리를 만들 파일 객체 생성
		File file=new File(filePath);
		if(!file.exists()){//디렉토리가 존재하지 않는다면
			file.mkdir();//디렉토리를 만든다.
		}
		//파일 시스템에 저장할 파일명을 만든다. (겹치치 않게)
		String saveFileName = System.currentTimeMillis()+orgFileName; //currentTimeMillis = 1970년 0시00분 기준으로 지금까지 흘러온 시간을 1/1000초단위로
		try{
			//upload 폴더에 파일을 저장한다.
			mFile.transferTo(new File(filePath+saveFileName));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//UsersDao 객체를 이용해서 프로파일 이미지
		//경로를 DB 에 저장하기
		String path="/upload/"+saveFileName;			
		//로그인된 아이디
		String id=(String)
				request.getSession().getAttribute("id");
		//아이디와 프로파일 이미지 경로를 dto 에 담고 
		UserDto dto=new UserDto();
		dto.setId(id);
		dto.setProfile(path);
		// UsersDao 를 이용해서 DB 에 반영하기 
		dao.updateProfile(dto);
		
		//이미지 경로 리턴해주기
		return path;
	}
}
