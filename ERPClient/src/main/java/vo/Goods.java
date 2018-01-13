package vo;

import java.io.Serializable;

/**
 * 
 * @author peng
 * 2017.12.5
 *
 */

public class Goods implements Serializable {

	//商品编号
    private String number;
    
    //名称
    private String name;
    
    //类型
    private String type;
    
    //购买数量
    private int quantity;
    
    //零售价
    private double price;

    public Goods(){
    	
    }
    
    public Goods(String number, String name, String type, int quantity, double price){
    	this.number=number;
    	this.name=name;
    	this.type=type;
    	this.quantity=quantity;
    	this.price=price;
    }

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
    
}
