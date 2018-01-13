package service.blservice;


import vo.Goods;
import objects.ResultMessage;
import vo.MemberVO;
import vo.PromotionVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *  by peng ,2017,12,4
 */
public interface PromotionBLService extends Remote{

	//渚濇嵁membervo锛屽拰goodsList锛屽緱鍑鸿禒鍝�
	public ArrayList<Goods> getGift(MemberVO member, ArrayList<Goods> goodsList)throws RemoteException;

	//渚濇嵁membervo锛屽拰goodsList锛屽緱鍑轰紭鎯犻噾棰�
	public double getReduction(MemberVO member, ArrayList<Goods> goodsList)throws RemoteException;

	//渚濇嵁membervo锛屽拰goodsList锛屽緱鍑轰唬閲戝嵎
	public double getCoupon(MemberVO member, ArrayList<Goods> goodsList)throws RemoteException;
	
	//娣诲姞淇冮攢鏂规
	public ResultMessage add(PromotionVO promotion)throws RemoteException;
	
	//鍒犻櫎淇冮攢鏂规
	public ResultMessage delete(PromotionVO promotion)throws RemoteException;
	
	//淇敼淇冮攢鏂规
	public ResultMessage update(PromotionVO promotion)throws RemoteException;
	
	public ArrayList<PromotionVO> getList()throws RemoteException;
	
	
}
