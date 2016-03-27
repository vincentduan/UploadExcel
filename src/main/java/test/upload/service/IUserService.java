package test.upload.service;

import java.util.List;

import test.upload.model.User;


public interface IUserService {
	public User getUserById(int userId);

	public List<User> getUserList();

	public void delUserById(Integer userId);
}
