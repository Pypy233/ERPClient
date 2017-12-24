package service.blservice;

import vo.GoodsVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * 库存不足报警并补充到正常数量
 */
public interface GoodsWarningMessageBLService extends Remote {
    public ArrayList<GoodsVO> getWarningMessage(int number)throws RemoteException;
}
