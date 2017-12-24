package rmi;

import service.blservice.*;

import java.rmi.Remote;

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
}
