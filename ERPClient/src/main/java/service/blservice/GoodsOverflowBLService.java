package service.blservice;

import objects.ResultMessage;
import vo.GoodsOverflowVO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GoodsOverflowBLService extends Remote {

    /**
     * 增加报溢商品
     * @param vo
     * @return
     */
    public ResultMessage addGoodsOverflow(GoodsOverflowVO vo)throws RemoteException;

    /**
     * 删除报溢商品
     * @param vo
     * @return
     */
    public ResultMessage deleteGoodsOverflow(GoodsOverflowVO vo)throws RemoteException;

    /**
     * 更新报溢商品
     * @param vo
     * @return
     */
    public ResultMessage update(GoodsOverflowVO vo)throws RemoteException;
}
