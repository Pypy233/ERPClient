package service.blservice;

import objects.ResultMessage;
import vo.LogVO;
import vo.UserVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface LogBLService extends Remote {
    /**
     * 增加日志
     * @param vo
     * @param operation
     * @param msg
     * @return
     */
    public ResultMessage addLog(UserVO vo, String operation, ResultMessage msg)throws RemoteException;

    /**
     * 删除日志
     * @param vo
     * @return
     */
    public ResultMessage delete(LogVO vo)throws RemoteException;

    /**
     * 更新日志
     * @param vo
     * @return
     */
    public ResultMessage update(LogVO vo)throws RemoteException;

    /**
     * 得到当前用户的日志
     * @param vo
     * @return
     */
    public ArrayList<LogVO> getLog(UserVO vo)throws RemoteException;

    /**
     * 按照用户名查找日志
     * @param name
     * @return
     */
    public ArrayList<LogVO> findByName(String name)throws RemoteException;

    /**
     * 按照日期查找日志
     * @param date
     * @return
     */
    public ArrayList<LogVO> findByDate(String date)throws RemoteException;

    /**
     * 按操作查找日志
     * @param operation
     * @return
     */
    public ArrayList<LogVO> findByOperation(String operation)throws RemoteException;

}
