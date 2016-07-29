package pers.gulo.fm.domain;

public class Statistic {
	private int weekFlight;
	private int monthFlight;
	private int totalFlight;
	
	private int weekOrder;
	private int monthOrder;
	private int totalOrder;
	
	private float weekIncome;
	private float monthIncome;
	private float totalIncome;
	public int getWeekFlight() {
		return weekFlight;
	}
	public void setWeekFlight(int weekFlight) {
		this.weekFlight = weekFlight;
	}
	public int getMonthFlight() {
		return monthFlight;
	}
	public void setMonthFlight(int monthFlight) {
		this.monthFlight = monthFlight;
	}
	public int getTotalFlight() {
		return totalFlight;
	}
	public void setTotalFlight(int totalFlight) {
		this.totalFlight = totalFlight;
	}
	public int getWeekOrder() {
		return weekOrder;
	}
	public void setWeekOrder(int weekOrder) {
		this.weekOrder = weekOrder;
	}
	public int getMonthOrder() {
		return monthOrder;
	}
	public void setMonthOrder(int monthOrder) {
		this.monthOrder = monthOrder;
	}
	public int getTotalOrder() {
		return totalOrder;
	}
	public void setTotalOrder(int totalOrder) {
		this.totalOrder = totalOrder;
	}
	public float getWeekIncome() {
		return weekIncome;
	}
	public void setWeekIncome(float weekIncome) {
		this.weekIncome = weekIncome;
	}
	public float getMonthIncome() {
		return monthIncome;
	}
	public void setMonthIncome(float monthIncome) {
		this.monthIncome = monthIncome;
	}
	public float getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(float totalIncome) {
		this.totalIncome = totalIncome;
	}
	@Override
	public String toString() {
		return "Statistic [weekFlight=" + weekFlight + ", monthFlight="
				+ monthFlight + ", totalFlight=" + totalFlight + ", weekOrder="
				+ weekOrder + ", monthOrder=" + monthOrder + ", totalOrder="
				+ totalOrder + ", weekIncome=" + weekIncome + ", monthIncome="
				+ monthIncome + ", totalIncome=" + totalIncome + "]";
	}
	
	

	
	

}
