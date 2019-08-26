package com.acorn.acorngram.user.dao;

import com.acorn.acorngram.user.dto.UserDto;


public interface UserDao {
	public boolean signUp(UserDto dto);
	public String getPwdHash(String id);
	public UserDto getData(String id);
	public boolean isExist(String id);
	public void delete(String id);
	public boolean updateProfile(UserDto dto);
	public boolean updateUser(UserDto dto);
	public boolean updateUserEmail(UserDto dto);
	public boolean updateUserPwd(UserDto dto);
	
	
}
