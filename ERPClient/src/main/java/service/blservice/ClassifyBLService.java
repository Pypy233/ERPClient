package service.blservice;

import objects.ResultMessage;
import vo.ClassifyVO;
import vo.GoodsVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by py on 2017/10/19.
 */
public interface ClassifyBLService extends Remote{
   /**
    * 增加商品分类
    * @param vo
    * @return
    * @throws RemoteException
    */
   public ResultMessage addClassify(ClassifyVO vo) throws RemoteException;

   /**
    * 删除商品分类
    * @param vo
    * @return
    * @throws RemoteException
    */
   public ResultMessage deleteClassify(ClassifyVO vo) throws RemoteException;

   /**
    * 修改商品分类
    * @param vo
    * @return
    * @throws RemoteException
    */
   public ResultMessage updateClassify(ClassifyVO vo) throws RemoteException;

   /**
    *获取一个商品分类下的所有商品
    * @param vo
    * @return
    */
   public ArrayList<GoodsVO> getGoods(ClassifyVO vo) throws RemoteException;


}
