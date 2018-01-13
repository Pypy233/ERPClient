package service.blservice;

import objects.ResultMessage;
import vo.PresentListVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PresentListBLService extends Remote {
    /**
     *
     * @param vo
     * @return
     */
    public ResultMessage addPresentList(PresentListVO vo)throws RemoteException;

    /**
     *
     * @param vo
     * @return
     */
    public ResultMessage deletePresentList(PresentListVO vo)throws RemoteException;

    /**
     *
     * @param vo
     * @return
     */
    public ResultMessage update(PresentListVO vo)throws RemoteException;


    /**
     * 查找并得到赠送单
     * @param startTime
     * @param endTime
     * @param userName
     * @param memberName
     * @return
     */
    public ArrayList<PresentListVO> getPresentList(String startTime, String endTime,
                                                   String userName, String memberName)throws RemoteException;

    /**
     * 生成红冲
     * @param vo
     * @return
     */
    public PresentListVO addPresentListRed(PresentListVO vo)throws RemoteException;

    /**
     * 获取所有礼品单
     * @return
     * @throws RemoteException
     */
    public ArrayList<PresentListVO> getAllPresentList() throws RemoteException;
}
