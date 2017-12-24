package service.blservice;

import objects.ResultMessage;
import vo.MemberVO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by py on 2017/10/20.
 * 管理员工信息的类
 */
public interface MemberBLService extends Remote {
    /**
     * 增加员工信息
     * @param vo
     * @return
     */
    public ResultMessage addMember(MemberVO vo) throws RemoteException;

    /**
     * 删除员工信息
     * @param vo
     * @return
     */
    public ResultMessage deleteMember(MemberVO vo) throws RemoteException;

    /**
     * 更新员工信息
     * @param vo
     * @return
     */
    public ResultMessage updateMember(MemberVO vo) throws RemoteException;

    /**
     * 根据id查找员工
     * @param number
     * @return
     */
    public MemberVO findMember(int number)throws RemoteException;


}
