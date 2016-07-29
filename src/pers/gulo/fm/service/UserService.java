package pers.gulo.fm.service;

import pers.gulo.fm.domain.User;
import pers.gulo.fm.exception.FMException;

public interface UserService {
	/**
	 * 用户登录，成功返回用户信息，失败返回null
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username,String password);
	
	public void addUser(User user) throws FMException;

	public void recharge(User user, float number);
}
