package stockExchange.strategy;

import java.util.List;

import stockExchange.to.OrderRequest;

public interface GameStrategy {

	List<OrderRequest> listOfRequests();
	String getName();
}
