package stockExchange.service;

import java.util.List;

import stockExchange.to.QuotationObject;

public interface StockExchangeService {
	List<QuotationObject> findQuotationByName(String name);
	List<QuotationObject> findQuotationByDay(String day);
	List<String> getAllDays();
}