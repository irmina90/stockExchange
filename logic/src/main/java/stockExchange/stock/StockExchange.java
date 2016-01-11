package stockExchange.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.service.StockExchangeService;
import stockExchange.to.QuotationObject;

@Component
public class StockExchange {
	private final int FIRST_DAY_NUMBER = 0;
	@Autowired
	private StockExchangeService service;
	private List<String> days;
	private List<QuotationObject> currentQuotations;
	private String currentDate;
	private int currentDayNumber;

	public StockExchange() {

	}

	public void setFirstData() {
		setDays(service.getAllDays());
		setCurrentDayNumber(FIRST_DAY_NUMBER);
		setCurrentQuotations(service.findQuotationByDay(getCurrentDay()));
	}

	public String getCurrentDay() {
		if (getDays().isEmpty() || noMoreDays())
			return null;
		else
			return getDays().get(getCurrentDayNumber());
	}

	private boolean noMoreDays(){
		return getAmountOfDays() == getCurrentDayNumber();
	}
	
	public String getNextDay() {
		setCurrentDayNumber(getCurrentDayNumber() + 1);
		if (noMoreDays())
			return null;
		else
			return getDays().get(getCurrentDayNumber());
	}

	public int getAmountOfDays() {
		if (getDays().isEmpty())
			return 0;
		else
			return getDays().size();
	}

	public List<QuotationObject> getCurrentQuotations() {
		return this.currentQuotations;
	}

	public void setCurrentQuotations(List<QuotationObject> currentQuotations) {
		this.currentQuotations = currentQuotations;
	}

	public StockExchangeService getService() {
		return service;
	}

	public void setService(StockExchangeService service) {
		this.service = service;
	}

	public List<String> getDays() {
		return days;
	}

	public void setDays(List<String> days) {
		this.days = days;
	}

	public int getCurrentDayNumber() {
		return currentDayNumber;
	}

	public void setCurrentDayNumber(int currentDayNumber) {
		this.currentDayNumber = currentDayNumber;
	}

}
