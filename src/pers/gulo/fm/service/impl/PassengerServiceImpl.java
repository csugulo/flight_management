package pers.gulo.fm.service.impl;

import java.util.List;

import pers.gulo.fm.dao.PassengerDao;
import pers.gulo.fm.dao.impl.PassengerDaoImpl;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.service.PassengerService;

public class PassengerServiceImpl implements PassengerService{

	@Override
	public void bookFlight(User user, Flight flight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelBook(User user, Flight flight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pay(User user, Flight flight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Flight> listUnBookedFlight(User user) {
		PassengerDao pDao=new PassengerDaoImpl();
		return pDao.getUnBookedFlight(user);
	}

}
