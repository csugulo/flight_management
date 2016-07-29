package pers.gulo.fm.domain;

import java.sql.Timestamp;
import java.util.Date;

public class Order {
	private int no;
	private User user;
	private Flight flight;
	private boolean isPayed;
	private boolean isCanceled;
	private Timestamp time;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public boolean getIsPayed() {
		return isPayed;
	}
	public void setIsPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}
	public boolean getIsCanceled() {
		return isCanceled;
	}
	public void setIsCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Order [no=" + no + ", user=" + user + ", flight=" + flight
				+ ", isPayed=" + isPayed + ", isCanceled=" + isCanceled
				+ ", time=" + time + "]";
	}


	
}
