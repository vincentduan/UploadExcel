package test.upload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.upload.dao.UserMapper;
import test.upload.model.User;
import test.upload.model.UserExample;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;

	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userId);
	}

	public List<User> getUserList() {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		return userMapper.selectByExample(example);
	}

	public void delUserById(Integer userId) {
		// TODO Auto-generated method stub
		userMapper.deleteByPrimaryKey(userId);
	}

	/*@Override
	public User getUserById(int userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public List<User> getUserList() {
		UserExample example = new UserExample();
		return userMapper.selectByExample(example);
	}

	@Override
	public void delUserById(Integer userId) {
		userMapper.deleteByPrimaryKey(userId);
	}*/

}
