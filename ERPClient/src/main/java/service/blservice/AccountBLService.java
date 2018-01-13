package service.blservice;

import objects.ResultMessage;
import vo.AccountVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AccountBLService  extends Remote{
	/**
	 *  by peng ,2017,12,4
	 */
	
	//用它能拿到所有的初账
	public ArrayList<AccountVO> getList()throws RemoteException;
	
	//新建并添加
	public ResultMessage create()throws RemoteException;
	

}
