package vo;

import java.io.Serializable;
import java.util.ArrayList;


public class CashVO implements Serializable {
	//created by peng 
	String number;
	UserVO operator;//鎿嶄綔鍛�
	String name_bank;
	ArrayList<CashRecord> recordList;//鏀嚭鍒楄〃
	double sum;//鎬婚
	String date;
	String state;
	public CashVO(){}
	
	public CashVO(String number,UserVO operator,String name_bank,ArrayList<CashRecord> recordList,double sum,String date,String state){
		this.date=date;
		this.number=number;
		this.operator=operator;
		this.name_bank=name_bank;
		this.recordList=recordList;
		this.sum=sum;
	}




	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public UserVO getOperator() {
		return operator;
	}


	public void setOperator(UserVO operator) {
		this.operator = operator;
	}


	public String getName_bank() {
		return name_bank;
	}


	public void setName_bank(String name_bank) {
		this.name_bank = name_bank;
	}


	public ArrayList<CashRecord> getRecordList() {
		return recordList;
	}


	public void setRecordList(ArrayList<CashRecord> recordList) {
		this.recordList = recordList;
	}


	public double getSum() {
		return sum;
	}


	public void setSum(double sum) {
		this.sum = sum;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	

}


