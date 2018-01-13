package vo;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

public class AccountVO implements Serializable{
	//created by peng 
	String date;
	ArrayList<GoodsVO> GoodsList;//商品列表
	ArrayList<BankVO> BankList;//银行列表
	ArrayList<MemberVO> MemberList;//会员列表
	
	public AccountVO(){}
	
	public AccountVO(ArrayList<GoodsVO> GoodsList,ArrayList<BankVO> BankList,ArrayList<MemberVO> MemberList,String date){
		this.GoodsList = GoodsList ;
		this.BankList= BankList;
		this.MemberList= MemberList;
		this.date=date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<GoodsVO> getGoodsList() {
		return GoodsList;
	}

	public void setGoodsList(ArrayList<GoodsVO> goodsList) {
		GoodsList = goodsList;
	}

	public ArrayList<BankVO> getBankList() {
		return BankList;
	}

	public void setBankList(ArrayList<BankVO> bankList) {
		BankList = bankList;
	}

	public ArrayList<MemberVO> getMemberList() {
		return MemberList;
	}

	public void setMemberList(ArrayList<MemberVO> memberList) {
		MemberList = memberList;
	}
	
	
}






