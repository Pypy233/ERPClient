package service.blservice;

import objects.ResultMessage;
import vo.GoodsSaleReturnVO;
import vo.SaleReturnVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

public interface SaleReturnBLService extends Remote {

    /**
     * 增加销售退货单
     * @param retailer
     * @param salesman
     * @param operator
     * @param remark
     * @param set
     * @return
     * @throws RemoteException
     */
    public ResultMessage addSaleReturn(String retailer, String salesman, String operator,
                                       String remark, Set<GoodsSaleReturnVO> set) throws RemoteException;

    /**
     * 删除销售退货单
     * @param vo
     * @return
     * @throws RemoteException
     */
    public ResultMessage deleteSaleReturn(SaleReturnVO vo) throws RemoteException;

    /**
     * 更新销售退货单
     * @param vo
     * @return
     * @throws RemoteException
     */
    public ResultMessage updateSaleReturn(SaleReturnVO vo) throws RemoteException;

    /**
     * 得到所有提交的单据
     * @return
     */
    public ArrayList<SaleReturnVO> getSaleReturnProcessList() throws RemoteException;


    /**
     * 通过审批
     * @param vo
     * @throws RemoteException
     */
    public void passSaleReturnCheck(SaleReturnVO vo) throws RemoteException;

    /**
     * 没通过审批
     * @param vo
     * @throws RemoteException
     */
    public void failSaleReturnCheck(SaleReturnVO vo) throws  RemoteException;

    /**
     * 查找并得到销售单
     * @param startTime
     * @param endTime
     * @param userName
     * @param memberName
     * @return
     */
    public ArrayList<SaleReturnVO> getSaleReturn(String startTime, String endTime,
                                                 String userName, String memberName) throws RemoteException;

    /**
     * 得到未通过销售单
     * @return
     */
    public ArrayList<SaleReturnVO> getSaleReturnFail() throws RemoteException;


    /**
     * 生成红冲
     * @return
     * @throws RemoteException
     */
    public SaleReturnVO addSaleReturnRed(SaleReturnVO vo) throws RemoteException;



}
