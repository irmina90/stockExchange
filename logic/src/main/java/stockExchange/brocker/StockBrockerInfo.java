package stockExchange.brocker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.to.QuotationObject;

@Component
public class StockBrockerInfo {
	@Autowired
	private StockBrocker stockBrocker;

	public StockBrockerInfo(){
		
	}
	
	public StockBrocker getStockBrocker() {
		return stockBrocker;
	}

	public void setStockBrocker(StockBrocker stockBrocker) {
		this.stockBrocker = stockBrocker;
	}
	
	public List<QuotationObject> getCurrentQuotations(){
		return this.stockBrocker.getStockExchange().getCurrentQuotations();
	}
}
