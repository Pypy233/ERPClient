package service.blservice;

import objects.ResultMessage;
import vo.GoodsStockVO;
import vo.StockVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by py on 2017/10/20.
 */
public interface StockBLService extends Remote {

   /**
    * 增加库存单
    * @param vo
    * @return
    * @throws RemoteException
    */
   public ResultMessage addStock(StockVO vo) throws RemoteException;

   /**
    * 增加库存单，重载
    * @param provider
    * @param remark
    * @param set
    * @return
    * @throws RemoteException
    */
   public ResultMessage addStock(String provider, String remark, Set<GoodsStockVO> set) throws RemoteException;

   /**
    * 删除库存单
    * @param vo
    * @return
    * @throws RemoteException
    */
   public ResultMessage deleteStock(StockVO vo) throws RemoteException;

   /**
    * 更新库存单
    * @param vo
    * @return
    * @throws RemoteException
    */
   public ResultMessage updateStock(StockVO vo) throws RemoteException;

   /**
    * 得到所有提交的单据
    * @return
    */
   public ArrayList<StockVO> getStockProcessList() throws RemoteException;


   /**
    * 通过审批
    * @param vo
    * @throws RemoteException
    */
   public void passStockCheck(StockVO vo) throws RemoteException;

   /**
    * 没通过审批
    * @param vo
    * @throws RemoteException
    */
   public void failStockCheck(StockVO vo) throws  RemoteException;

   /**
    * 查找并得到进货单
    * @param startTime
    * @param endTime
    * @param userName
    * @param memberName
    * @return
    */
   public ArrayList<StockVO> getStock(String startTime, String endTime, String userName,
                                      String memberName) throws RemoteException;

   /**
    * 得到未通过进货单
    * @return
    */
   public ArrayList<StockVO> getStockFail() throws RemoteException;

   /**
    * 生成红冲
    * @param vo
    * @return
    */
   public StockVO addStockRed(StockVO vo) throws RemoteException;

}
