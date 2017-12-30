package service.blservice;

import objects.ResultMessage;
import vo.AdminVO;
import vo.UserVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AdminBLService {

    /**
     * 增加神奇的管理员
     * @param vo
     * @return
     */
    public ResultMessage addAdmin(AdminVO vo) throws RemoteException;

    /**
     * 增加神奇的管理员
     * @param vo
     * @return
     */
    public ResultMessage deleteAdmin(AdminVO vo) throws RemoteException;


    /***
     * 增加神奇的管理员
     * @param vo
     * @return
     */
    public ResultMessage updateAdmin(AdminVO vo) throws RemoteException;

    /**
     * 神奇的管理员得到所有用户信息
     * @return
     */
    public ArrayList<UserVO> getAllUsers() throws RemoteException;

    /**
     * 按类别查找用户
     * @param type
     * @return
     */
    public ArrayList<UserVO> findByType(String type) throws RemoteException;

    /**
     * 增加高权限用户
     * @param vo
     * @return
     */
    public ResultMessage addAdvancedUser(UserVO vo) throws RemoteException;

    /**
     * 删除用户
     * @param vo
     * @return
     */
    public ResultMessage deleteUser(UserVO vo) throws RemoteException;
}
