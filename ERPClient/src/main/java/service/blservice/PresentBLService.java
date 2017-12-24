package service.blservice;

import objects.ResultMessage;
import vo.PresentVO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PresentBLService extends Remote {
    /**
     * 增加赠送商品
     * @param vo
     * @return
     */
    public ResultMessage addPresent(PresentVO vo)throws RemoteException;

    /**
     * 删除赠送商品
     * @param vo
     * @return
     */
    public ResultMessage deletePresent(PresentVO vo)throws RemoteException;

    /**
     * 更新赠送商品
     * @param vo
     * @return
     */
    public ResultMessage updatePresent(PresentVO vo)throws RemoteException;
}
