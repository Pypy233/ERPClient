package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class PromotionStrategy implements Serializable {
	ArrayList<Integer> levelList;
	double charge_min;
	double charge_max;
	ArrayList<Goods> giftList;
	double coupon;
	double discount;
	
	public PromotionStrategy(){}
	public PromotionStrategy(ArrayList<Integer> levelList, double charge_min, double charge_max,
                      ArrayList<Goods> giftList, double coupon, double discount){
		this.levelList=levelList;
		this.charge_min=charge_min;
		this.charge_max=charge_max;
		this.giftList=giftList;
		this.coupon=coupon;
		this.discount=discount;
	}

	public ArrayList<Integer> getLevelList() {
		return levelList;
	}

	public void setLevelList(ArrayList<Integer> levelList) {
		this.levelList = levelList;
	}

	public double getCharge_min() {
		return charge_min;
	}

	public void setCharge_min(double charge_min) {
		this.charge_min = charge_min;
	}

	public double getCharge_max() {
		return charge_max;
	}

	public void setCharge_max(double charge_max) {
		this.charge_max = charge_max;
	}

	public ArrayList<Goods> getGiftList() {
		return giftList;
	}

	public void setGiftList(ArrayList<Goods> giftList) {
		this.giftList = giftList;
	}

	public double getCoupon() {
		return coupon;
	}

	public void setCoupon(double coupon) {
		this.coupon = coupon;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	
}
