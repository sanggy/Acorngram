package com.acorn.acorngram.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn.acorngram.user.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired SqlSession session;
	
	@Override
	public boolean signUp(UserDto dto) {
		session.insert("users.insert", dto);
		return false;
	}
	
	//전달된 아이디에 해당하는 비번을 SELECT해서 리턴하는 메소드
		@Override
		public String getPwdHash(String id) {
			
			String pwdHash = session.selectOne("users.getPwdHash", id);
			
			return pwdHash;
		}

		@Override
		public UserDto getData(String id) {
			
			return session.selectOne("users.getData", id);
		}

		@Override
		public boolean isExist(String id) {
			String validId = session.selectOne("users.isExist", id);
			if(validId == null) {
				return false;
			}
			return true;
		}

		@Override
		public void delete(String id) {
			session.delete("users.delete", id);
		}

		@Override
		public boolean updateProfile(UserDto dto) {
			int isUpdate = session.update("user.updateProfile", dto);
			if(isUpdate > 0) {
				return true;
			}
			return false;
		}

		@Override
		public boolean updateUser(UserDto dto) {
			int isUpdate =  session.update("user.updateUser", dto);
			if(isUpdate > 0) {
				return true;
			}
			return false;
		}

		@Override
		public boolean updateUserEmail(UserDto dto) {
			int isUpdate = session.update("user.updateUser_email", dto);
			if(isUpdate > 0) {
				return true;
			}
			return false;
		}

		@Override
		public boolean updateUserPwd(UserDto dto) {
			int isUpdate = session.update("user.updateUser_pwd", dto);
			if(isUpdate > 0) {
				return true;
			}
			return false;
		}

}
