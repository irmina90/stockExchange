package stockExchange.to;

import java.math.BigDecimal;

public class OrderRequest {
	private int amount;
	private String name;
	private boolean buyOrder;
	private BigDecimal price;
	
	public OrderRequest(){
		
	}
	
	public OrderRequest(String name, int quantity, boolean buyOrder, BigDecimal price){
		this.name = name;
		this.amount = quantity;
		this.buyOrder = buyOrder;
		this.price = price;
	}

	public int getQuantity() {
		return amount;
	}

	public void setQuantity(int quantity) {
		this.amount = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isBuyOrder() {
		return buyOrder;
	}

	public void setBuyOrder(boolean buyOrder) {
		this.buyOrder = buyOrder;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
