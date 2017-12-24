package service.blservice;

import objects.ResultMessage;
import vo.OverflowListVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface OverflowListBLService extends Remote {
    /**
     * 增加报溢单
     * @param vo
     * @return
     */
    public ResultMessage addOverflowList(OverflowListVO vo)throws RemoteException;

    /**
     * 删除报溢单
     * @param vo
     * @return
     */
    public ResultMessage deleteOverlowList(OverflowListVO vo)throws RemoteException;

    /**
     * 更新报溢单
     * @param vo
     * @return
     */
    public ResultMessage updateOverflowList(OverflowListVO vo)throws RemoteException;

    /**
     * 查找得到报溢单
     * @param startTime
     * @param endTime
     * @param userName
     * @return
     */
    public ArrayList<OverflowListVO> getOverflowList(String startTime, String endTime, String userName)throws RemoteException;

    /**
     * 生成红冲
     * @param vo
     * @return
     */
    public OverflowListVO addOverflowListRed(OverflowListVO vo)throws RemoteException;
}
