package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class PromotionVO implements Serializable {
	ArrayList<BargainSet> bargainList;
	ArrayList<PromotionStrategy> strategyList;
	String begin;
	String end;

	public PromotionVO(){}

	public PromotionVO(ArrayList<BargainSet> bargainList, ArrayList<PromotionStrategy> strategyList, String begin, String end){
		this.bargainList=bargainList;
		this.strategyList=strategyList;
		this.begin=begin;
		this.end=end;
	}

	public ArrayList<BargainSet> getBargainList() {
		return bargainList;
	}

	public void setBargainList(ArrayList<BargainSet> bargainList) {
		this.bargainList = bargainList;
	}

	public ArrayList<PromotionStrategy> getStrategyList() {
		return strategyList;
	}

	public void setStrategyList(ArrayList<PromotionStrategy> strategyList) {
		this.strategyList = strategyList;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}