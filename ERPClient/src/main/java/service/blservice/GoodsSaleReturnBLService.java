package service.blservice;

import objects.ResultMessage;
import vo.GoodsSaleReturnVO;
import vo.GoodsVO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GoodsSaleReturnBLService  extends Remote {
    /**
     *增加销售退货商品，允许修改销售价格
     * @param vo
     * @param saleReturnNumber
     * @param price
     * @param remark
     * @return
     * @throws RemoteException
     */
    public ResultMessage addGoodsSaleReturn(GoodsVO vo, int saleReturnNumber, double price, String remark) throws RemoteException;

    /**
     * 删除销售退货商品
     * @param vo
     * @return
     * @throws RemoteException
     */
    public ResultMessage deleteGoodsSaleReturn(GoodsSaleReturnVO vo) throws RemoteException;

    /**
     * 更新销售退货商品
     * @param vo
     * @return
     * @throws RemoteException
     */
    public ResultMessage updateGoodsSaleReturn(GoodsSaleReturnVO vo) throws RemoteException;
}

