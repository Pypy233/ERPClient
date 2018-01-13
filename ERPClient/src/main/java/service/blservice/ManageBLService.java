package service.blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ManageBLService extends Remote{
	//得到收入
	double getIncome()throws RemoteException;
	//都到支出
	double getExpense()throws RemoteException;
}
