package service.blservice;

import objects.ResultMessage;
import vo.GoodsStockReturnVO;
import vo.GoodsVO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GoodsStockReturnBLService extends Remote {
    /**
     * 增加进货退货商品，默认更改价格
     * @param vo
     * @param stockReturnNumber
     * @param price
     * @param remark
     * @return
     */
    public ResultMessage addGoodsStockReturn(GoodsVO vo, int stockReturnNumber, double price, String remark) throws RemoteException;


    /**
     * 删除进货退货商品
     * @param vo
     * @return
     */
    public ResultMessage deleteGoodsStockReturn(GoodsStockReturnVO vo) throws RemoteException;

    /**
     * 更新进货退货商品
     * @param vo
     * @return
     * @throws RemoteException
     */
    public ResultMessage updateGoodsStockReturn(GoodsStockReturnVO vo) throws RemoteException;

}
