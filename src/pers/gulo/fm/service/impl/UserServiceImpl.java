package pers.gulo.fm.service.impl;

import pers.gulo.fm.dao.UserDao;
import pers.gulo.fm.dao.impl.UserDaoImpl;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public User login(String username, String password) {
		UserDao uDao=new UserDaoImpl();
		return uDao.login(username, password);
	}
	
	@Override
	public void addUser(User user) throws FMException {
		UserDao uDao=new UserDaoImpl();
		uDao.insertUser(user);
	}
	
	@Override
	public void recharge(User user, float number) {
		UserDao uDao=new UserDaoImpl();
		uDao.reCharge(user,number);
		
	}

}
