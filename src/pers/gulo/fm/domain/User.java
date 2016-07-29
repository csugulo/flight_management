package pers.gulo.fm.domain;

public class User {
	private int no;
	private String username;
	private String password;
	private String nickname;
	private String ID;
	private float balance;
	private int type;
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "User [no=" + no + ", username=" + username + ", password="
				+ password + ", nickname=" + nickname + ", ID=" + ID
				+ ", balance=" + balance + ", type=" + type + "]";
	}

	

}
