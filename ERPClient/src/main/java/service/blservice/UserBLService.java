package service.blservice;

import objects.ResultMessage;
import vo.UserVO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserBLService extends Remote {
    /**
     * 注册
     * @param vo
     * @return
     */
    public ResultMessage register(UserVO vo)throws RemoteException;

    /**
     * 检查用户名密码
     * @param name
     * @param password
     * @return
     */
    public ResultMessage check(String name, String password)throws RemoteException;

    /**
     * 修改密码
     * @param name
     * @param password
     * @param type
     * @return
     */
    public ResultMessage updatePassword(String name, String password, String type)throws RemoteException;

    /**
     * 得到用户信息
     * @param name
     * @return
     */
    public UserVO getUserVO(String name)throws RemoteException;
}
