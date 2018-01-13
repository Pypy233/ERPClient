package rmi;

import service.blservice.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class RemoteHelper {
    private Remote remote;
    private static RemoteHelper remoteHelper = new RemoteHelper();
    public static RemoteHelper getInstance(){
        return remoteHelper;
    }

    private RemoteHelper() {

    }

    public void setRemote(Remote remote){
        this.remote = remote;
    }

    public ClassifyBLService getClassifyBLService(){
        return (ClassifyBLService)remote;
    }

    public CommodityBLService getCommodityBLService(){
        return (CommodityBLService)remote;
    }

    public GoodsBLService getGoodsBLService(){
        return (GoodsBLService)remote;
    }

    public GoodsSaleBLService getGoodsSaleBLService(){
        return (GoodsSaleBLService)remote;
    }

    public GoodsSaleReturnBLService getGoodsSaleReturnBLService(){
        return (GoodsSaleReturnBLService)remote;
    }

    public GoodsStockBLService getGoodsStockBLService(){
        return (GoodsStockBLService)remote;
    }

    public GoodsStockReturnBLService getGoodsStockReturnBLService(){
        return (GoodsStockReturnBLService)remote;
    }

    public MemberBLService getMemberBLService(){
        return (MemberBLService)remote;
    }

    public SaleBLService getSaleBLService(){
        return (SaleBLService)remote;
    }

    public SaleReturnBLService getSaleReturnBLService(){
        return (SaleReturnBLService)remote;
    }

    public StockBLService getStockBLService(){
        return (StockBLService)remote;
    }

    public StockReturnBLService getStockReturnBLService(){
        return (StockReturnBLService)remote;
    }

    public UserBLService getUserBLService(){
        return (UserBLService)remote;
    }
    public LogBLService getLogBlService(){
        return (LogBLService)remote;
    }
    public AdminBLService getAdminBLService(){
        return (AdminBLService)remote;
    }
    public PresentBLService getPresentBLService(){
        return (PresentBLService)remote;
    }
    public PresentListBLService getPresentListBLService(){
        return (PresentListBLService)remote;
    }
    public GoodsOverflowBLService getGoodsOverflowBLService(){
        return (GoodsOverflowBLService)remote;
    }
    public GoodsLackBLService getGoodsLackBLService(){
        return (GoodsLackBLService)remote;
    }
    public OverflowListBLService getOverflowListBLService(){
        return (OverflowListBLService)remote;
    }
    public LackListBLService getLackListBLService(){
        return (LackListBLService)remote;
    }
    public GoodsWarningMessageBLService getGoodsWarningMessageBLService(){
        return (GoodsWarningMessageBLService)remote;
    }


    public AccountBLService getAccountBLService() throws MalformedURLException, RemoteException, NotBoundException {
        return (AccountBLService)Naming.lookup("rmi://127.0.0.1:8887/account");
    }

    public PromotionBLService getPromotionBLService() throws MalformedURLException, RemoteException, NotBoundException {
        return (PromotionBLService) Naming.lookup("rmi://127.0.0.1:8887/promotion");
    }

    public BankBLService getBankBLService() throws MalformedURLException, RemoteException, NotBoundException{
        return (BankBLService)Naming.lookup("rmi://127.0.0.1:8887/bank");}

    public ManageBLService getManageBLService() throws MalformedURLException, RemoteException, NotBoundException{
        return (ManageBLService)Naming.lookup("rmi://127.0.0.1:8887/manage");}

    public TradeBLService getTradeBLService() throws MalformedURLException, RemoteException, NotBoundException{
        return (TradeBLService)Naming.lookup("rmi://127.0.0.1:8887/trade");}

    public CashBLService getCashBLService() throws MalformedURLException, RemoteException, NotBoundException{
        return (CashBLService)Naming.lookup("rmi://127.0.0.1:8887/cash"); }





}
