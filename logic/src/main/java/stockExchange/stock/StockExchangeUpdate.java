package stockExchange.stock;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class StockExchangeUpdate {
	@Autowired
	private StockExchange stockExchange;
	
	@Pointcut("execution(* stockExchange.brocker.StockBrocker.order(..))")
	public void update(){
	 
	}
	@After("update()")
	public void updateStockExchange() {
		if (!stockExchange.getCurrentDay().isEmpty()) {
			stockExchange.setCurrentQuotations(stockExchange.getService().findQuotationByDay(stockExchange.getNextDay()));
		}
	}
}
