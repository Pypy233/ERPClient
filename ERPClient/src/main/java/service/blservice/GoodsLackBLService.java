package service.blservice;
/**
 * 报损商品的bl
 */

import objects.ResultMessage;
import vo.GoodsLackVO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GoodsLackBLService extends Remote{
    /**
     * 赠送报损商品
     * @param vo
     * @return
     */
    public ResultMessage addGoodsLack(GoodsLackVO vo)throws RemoteException;

    /**
     * 减少报损商品
     * @param vo
     * @return
     */
    public ResultMessage deleteGoodsLack(GoodsLackVO vo)throws RemoteException;

    /**
     * 更新报损商品
     * @param vo
     * @return
     */
    public ResultMessage update(GoodsLackVO vo)throws RemoteException;
}
