package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class BargainSet implements Serializable {

	ArrayList<Goods> goodsList;
	double discount;
	
	public BargainSet(){}
	
	public BargainSet(ArrayList<Goods> goodsList_bargain, double discount){
		this.goodsList=goodsList_bargain;
		this.discount=discount;
	}

	public ArrayList<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(ArrayList<Goods> goodsList_bargain) {
		this.goodsList = goodsList_bargain;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	
}
