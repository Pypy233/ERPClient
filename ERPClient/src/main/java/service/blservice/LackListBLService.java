package service.blservice;

import objects.ResultMessage;
import vo.LackListVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface LackListBLService extends Remote {

    /**
     * 增加报损单
     * @param vo
     * @return
     */
    public ResultMessage addLackList(LackListVO vo)throws RemoteException;

    /**
     * 删除报损单
     * @param vo
     * @return
     */
    public ResultMessage deleteLackList(LackListVO vo)throws RemoteException;

    /**
     * 更新报损单
     * @param vo
     * @return
     */
    public ResultMessage update(LackListVO vo)throws RemoteException;

    /**
     * 查找报损单
     * @param startTime
     * @param endTime
     * @param userName
     * @return
     */
    public ArrayList<LackListVO> getLackList(String startTime, String endTime, String userName)throws RemoteException;

    /**
     * 增加红冲
     * @param vo
     * @return
     */
    public LackListVO addLackListRed(LackListVO vo)throws RemoteException;

    /**
     * 得到所有报溢单
     * @return
     * @throws RemoteException
     */
    public ArrayList<LackListVO> getAllLackList() throws RemoteException;
}
