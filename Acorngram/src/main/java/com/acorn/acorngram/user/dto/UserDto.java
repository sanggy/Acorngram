package com.acorn.acorngram.user.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UserDto {
	private String userCode, id, name, email, password;
	private LocalDateTime regdate;
	
	private int postCount, totalLikeCount; 
	
	private List<String> followers;
	
	private String profile; 

	public UserDto() { }
	
	public UserDto(String userCode, String id, String name, String email, String password, LocalDateTime regdate,
			int postCount, int totalLikeCount, List<String> followers, String profile) {
		super();
		this.userCode = userCode;
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.regdate = regdate;
		this.postCount = postCount;
		this.totalLikeCount = totalLikeCount;
		this.followers = followers;
		this.profile = profile;
	}



	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getRegdate() {
		return regdate;
	}

	public void setRegdate(LocalDateTime regdate) {
		this.regdate = regdate;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public int getTotalLikeCount() {
		return totalLikeCount;
	}

	public void setTotalLikeCount(int totalLikeCount) {
		this.totalLikeCount = totalLikeCount;
	}

	public List<String> getFollowers() {
		return followers;
	}

	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	
	
}
