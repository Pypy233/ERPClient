package service.blservice;

import objects.ResultMessage;
import vo.GoodsSaleVO;
import vo.GoodsVO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GoodsSaleBLService extends Remote {
    /**
     *增加销售商品，允许修改销售价格
     * @param vo
     * @param saleNumber
     * @param price
     * @param remark
     * @return
     * @throws RemoteException
     */
    public ResultMessage addGoodsSale(GoodsVO vo, int saleNumber, double price, String remark) throws RemoteException;

    /**
     * 删除销售商品
     * @param vo
     * @return
     * @throws RemoteException
     */
    public ResultMessage deleteGoodsSale(GoodsSaleVO vo) throws RemoteException;

    /**
     * 更新销售商品
     * @param vo
     * @return
     * @throws RemoteException
     */
    public ResultMessage updateGoodsSale(GoodsSaleVO vo) throws RemoteException;
}
