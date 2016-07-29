package pers.gulo.fm.dao;

import java.util.List;
import java.util.Map;

import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.exception.FMException;

public interface UserDao {
	/**
	 * 根据用户名密码检查登录，若查询到用户则返回该用户信息，若无结果返回null。
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username,String password);
	
	public void insertUser(User user) throws FMException;

	public void reCharge(User user, float number); 

}
