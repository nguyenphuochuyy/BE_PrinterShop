package dao;
//import java.util.List;

import java.util.List;

import entity.User;

public interface UserDAO {
	public void insertUser(User user);
	public List<User> getAllUser();
	public String getRoleUser(String username);
	public boolean checkLogin(String username, String password);
	public User getUserByUsername(String username);
	public boolean addUser(User user);
	
}	
