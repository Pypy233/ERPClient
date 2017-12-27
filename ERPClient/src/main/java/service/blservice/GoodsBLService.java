package service.blservice;

/**
 * Created by py on 2017/10/19.
 * 为商品提供的服务
 */
import objects.ResultMessage;
import vo.GoodsVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GoodsBLService extends Remote {
    /**
     *增加商品
     * @param vo
     * @return
     */
    public ResultMessage addGoods(GoodsVO vo) throws RemoteException;

    /**
     *删除商品
     * @param vo
     * @return
     */
    public ResultMessage deleteGoods(GoodsVO vo) throws RemoteException;

    /**
     *修改商品
     * @param vo
     * @return
     */
    public ResultMessage updateGoods(GoodsVO vo) throws RemoteException;

    /**
     *根据商品编号精确查找，根据名称和型号提供模糊查找
     * @param number
     * @param name
     * @param type
     * @return
     */
    public ArrayList<GoodsVO> findGoods(String number, String name, String type) throws RemoteException;

    /**
     * 得到当天库存商品
     * @return
     */
    public ArrayList<GoodsVO> getCurrentGoods() throws RemoteException;



}
