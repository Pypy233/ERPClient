package service.blservice;

import java.util.ArrayList;
import vo.*;
import objects.ResultMessage;
//created by pg,edited by xyh


/**
 *  by peng ,2017,12,4
 */
public interface CheckBLService {

	public ArrayList<SaleVO> getSaleList();
	public ResultMessage pass(SaleVO sale);
	public ResultMessage unpassSale(SaleVO sale);
	public ResultMessage changeSale(SaleVO sale);
	
	public ArrayList<SaleReturnVO> getSaleReturnList();
	public ResultMessage pass(SaleReturnVO saleReturn);
	public ResultMessage unpassSaleReturn(SaleReturnVO saleReturn);
	public ResultMessage changeSaleReturn(SaleReturnVO saleReturn);

	public ArrayList<StockVO> getStockList();
	public ResultMessage pass(StockVO stock);
	public ResultMessage unpassStock(StockVO stock);
	public ResultMessage changeStock(StockVO stock);

	public ArrayList<StockReturnVO> getStockReturnList();
	public ResultMessage pass(StockReturnVO stockReturn);
	public ResultMessage unpassStockReturn(StockReturnVO stockReturn);
	public ResultMessage changeStockReturn(StockReturnVO stockReturn);

	public ArrayList<TradeVO> getTradeList();
	public ResultMessage pass(TradeVO trade);
	public ResultMessage unpassTrade(TradeVO trade);
	public ResultMessage changeTrade(TradeVO trade);

	public ArrayList<CashVO> getCashList();
	public ResultMessage pass(CashVO cash);
	public ResultMessage unpassPay(CashVO cash);
	public ResultMessage changePay(CashVO cash);

	
}
