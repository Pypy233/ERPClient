package vo;

import java.io.Serializable;

public class TradeRecord implements Serializable {

	String bankName;
	double money;;
	String remark;
	
	public TradeRecord(){}
	public TradeRecord(String b, double m, String r){
		this.bankName=b;
		this.money=m;
		this.remark=r;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
