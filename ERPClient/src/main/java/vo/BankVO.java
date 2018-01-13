package vo;


import java.io.Serializable;

public class BankVO implements Serializable {
	//created by peng 
	String name;
	double balance;//余额
	
	public BankVO(){}
	
	public BankVO(String name,double balance){
		this.name=name;
		this.balance=balance;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
