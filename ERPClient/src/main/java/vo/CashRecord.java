package vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "CashRecord")
public class CashRecord implements Serializable {

	String task;
	double money;
	String remark;

	public CashRecord(String t,double m,String r){
		this.task=t;
		this.money=m;
		this.remark=r;
	}
	
	public CashRecord(){}
	@Id
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
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
