package service.blservice;

import objects.ResultMessage;
import vo.TradeVO;

import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface TradeBLService extends Remote{
	/**
	 *  by peng ,2017,12,4
	 */
	//得到新的收款单编号，包含类型，时间，号码
	public String getNewnumber_collect()throws RemoteException, MalformedURLException;
	
	//得到新的付款单编号，包含类型，时间，号码
	public String getNewnumber_pay()throws RemoteException, MalformedURLException;
	//以下同CashBLService
	public ResultMessage upload(TradeVO trade)throws RemoteException, MalformedURLException;
	
	//
	public ResultMessage addDraft(TradeVO trade)throws RemoteException, MalformedURLException;
	
	//
	public ResultMessage deleteDraft(TradeVO trade)throws RemoteException, MalformedURLException;
		
	//
	public ResultMessage updateDraft(TradeVO trade)throws RemoteException,  MalformedURLException;
	
	public ArrayList<TradeVO> findCollect(String begin, String end, String member, String operator)throws RemoteException, MalformedURLException;

	public ArrayList<TradeVO> findPay(String begin, String end, String member, String operator)throws RemoteException, MalformedURLException;

	//得到所有收款单
	public ArrayList<TradeVO> getAllList()throws RemoteException, MalformedURLException;

	public ArrayList<TradeVO> getProcessedCollectList()throws RemoteException, MalformedURLException;

	public ArrayList<TradeVO> getProcessedPayList()throws RemoteException, MalformedURLException;

	//对通过审核的收款单进行处理，调用memberbl.execute
	public ResultMessage pass(TradeVO Trade)throws RemoteException, MalformedURLException;

	//对通过审核的收款单进行处理，调用bankbl.rollback
	public ResultMessage unpass(TradeVO Trade)throws RemoteException, MalformedURLException;

	//红冲操作，生成一个一摸一样但值取负的单据
	public ResultMessage red_collect(TradeVO trade)throws RemoteException, MalformedURLException;
	
	public ResultMessage red_pay(TradeVO trade)throws RemoteException, MalformedURLException;
	
	
	
}

