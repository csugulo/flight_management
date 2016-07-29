package pers.gulo.fm.domain;

import java.io.Serializable;

public class AirPlane implements Serializable{
	private int no;
	private String model;
	private int capacity;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	@Override
	public String toString() {
		return "AirPlane [no=" + no + ", model=" + model + ", capacity="
				+ capacity + "]";
	}
	
	
}
