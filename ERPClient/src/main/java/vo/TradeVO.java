package vo;

import java.io.Serializable;
import java.util.ArrayList;


public class TradeVO  implements Serializable {
	//created by peng 
	String type;
	String number;
	MemberVO member;//供应商
	UserVO operator;//操作员
	ArrayList<TradeRecord> recordList;//收入列表
	double sum;//总额
	String date;
	String state;
	
	public TradeVO(){}
	
	public TradeVO(String type,String number,MemberVO member,UserVO operator,ArrayList<TradeRecord> recordList,double sum,String date,String state){
		this.type=type;
		this.date=date;
		this.number=number;
		this.member=member;
		this.operator=operator;
		this.recordList=recordList;
		this.sum=sum;
		this.state=state;
	}

	


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}

	public UserVO getOperator() {
		return operator;
	}

	public void setOperator(UserVO operator) {
		this.operator = operator;
	}

	public ArrayList<TradeRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(ArrayList<TradeRecord> recordList) {
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


