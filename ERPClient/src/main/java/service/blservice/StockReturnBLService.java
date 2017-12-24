package service.blservice;

import objects.ResultMessage;
import vo.GoodsStockReturnVO;
import vo.StockReturnVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

public interface StockReturnBLService extends Remote {
    /**
     * 增加库存退货单
     * @param provider
     * @param remark
     * @param set
     * @return
     * @throws RemoteException
     */
    public ResultMessage addStockReturn(String provider, String remark, Set<GoodsStockReturnVO> set) throws RemoteException;

    /**
     * 删除库存退货单
     * @param vo
     * @return
     * @throws RemoteException
     */
    public ResultMessage deleteStockReturn(StockReturnVO vo) throws RemoteException;


    /**
     * 更新库存退货单
     * @param vo
     * @return
     * @throws RemoteException
     */
    public ResultMessage updateStockReturn(StockReturnVO vo) throws RemoteException;

    /**
     * 得到所有提交的单据
     * @return
     */
    public ArrayList<StockReturnVO> getStockReturnProcessList() throws RemoteException;


    /**
     * 通过审批
     * @param vo
     * @throws RemoteException
     */
    public void passStockReturnCheck(StockReturnVO vo) throws RemoteException;


    /**
     * 没通过审批
     * @param vo
     * @throws RemoteException
     */
    public void failStockReturnCheck(StockReturnVO vo) throws  RemoteException;

    /**
     * 查找并得到销售单
     * @param startTime
     * @param endTime
     * @param userName
     * @param memberName
     * @return
     */
    public ArrayList<StockReturnVO> getStockReturn(String startTime, String endTime, String userName, String memberName)
            throws RemoteException;

    /**
     * 得到未通过销售单
     * @return
     */
    public ArrayList<StockReturnVO> getStockReturnFail() throws RemoteException;

}
