package stockExchange.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stockExchange.mapper.Mapper;
import stockExchange.repository.StockExchangeRepository;
import stockExchange.service.StockExchangeService;
import stockExchange.to.QuotationObject;

@Service
public class StockExchangeServiceImpl implements StockExchangeService {
	@Autowired
	private StockExchangeRepository repository;

	public StockExchangeServiceImpl() {
		
	}

	public List<QuotationObject> findQuotationByName(String name) {
		return Mapper.map2To(repository.findQuotationsByName(name));
	}

	public List<QuotationObject> findQuotationByDay(String day) {
		return Mapper.map2To(repository.findQuotationsByDay(day));
	}

	public List<String> getAllDays() {
		return Mapper.mapDays(repository.findAll());
	}
}
