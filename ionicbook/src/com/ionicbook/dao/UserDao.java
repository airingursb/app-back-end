package com.ionicbook.dao;

import com.ionicbook.entity.User;

public interface UserDao {

	public User getUser(int userId);
	
	public int getUserIdByUserAccount(String userAccount);
	
	public boolean login(String userAccount, String userPassword);
	
	public void saveUser(User user);
}
