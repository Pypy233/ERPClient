package service.blservice;

import objects.ResultMessage;
import vo.CashVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *  by peng ,2017,12,4
 */
public interface CashBLService extends Remote{
	
	//得到新的现金单编号，包括类型，时间，号码
	public String getNewnumber()throws RemoteException;
	//添加现金单，添加draft
	public ResultMessage addDraft(CashVO cash)throws RemoteException;
		
	//删除现金单，只能删除未提交状态的单据
	public ResultMessage deleteDraft(CashVO cash)throws RemoteException;
	
	//更新现金单,只能修改draft
	public ResultMessage updateDraft(CashVO cash)throws RemoteException;
	
	//得到所有收款单
	public ArrayList<CashVO> getAllList()throws RemoteException;
		
	//得到已上传但未审核的单据
	public ArrayList<CashVO> getProcessedList()throws RemoteException;
		
	//条件查找单据
	public ArrayList<CashVO> findCash(String begin, String end, String operator)throws RemoteException;

	//上传现金单（草稿，非草稿均可）
	public ResultMessage upload(CashVO cash)throws RemoteException;

	//对通过审核的现金单进行处理，
	public ResultMessage pass(CashVO cash)throws RemoteException;

	//对未通过审核的现金单进行处理。
	public ResultMessage unpass(CashVO cash)throws RemoteException;

	//红冲操作，生成一个一摸一样但值取负的单据入库，只能红冲通过的单据
	public ResultMessage red(CashVO cash)throws RemoteException;
	
	//如果要进行其它操作，请组合以上方法达成目的。
	//如果在审核时进行修改，可以1.unpass2，upload3.pass，这三步完成
	
}
