package service.blservice;

import objects.ResultMessage;
import vo.BankVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface BankBLService extends Remote{
	/**
	 *  by peng ,2017,12,4
	 */
	
	//根据bankvo添加一个新的银行账户，
	public ResultMessage add(BankVO bank)throws RemoteException;
	
	//根据bankvo修改这个银行账户
	public ResultMessage update(BankVO bank)throws RemoteException;
	
	//根据bankvo删除这个银行账户
	public ResultMessage delete(BankVO bank)throws RemoteException;
	
	//根据keywords（这里应该是账户名称）得到该账户的bankvo
	public BankVO get(String name)throws RemoteException;
	
	//得到所有银行账户的bankvo
	public ArrayList<BankVO> getList()throws RemoteException;

	public ArrayList<BankVO> getList(String key)throws RemoteException;
	
	
}
