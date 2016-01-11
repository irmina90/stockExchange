package stockExchange.strategy.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.brocker.StockBrockerInfo;
import stockExchange.strategy.GameStrategy;
import stockExchange.to.OrderRequest;
import stockExchange.to.QuotationObject;
import stockExchange.to.WalletStock;
import stockExchange.wallet.WalletInfo;

@Component
public class RandomStrategy implements GameStrategy {
	private final String name = "RANDOM STRATEGY";
	private final boolean isBuy = true;
	@Autowired
	private StockBrockerInfo stockExchangeInfo;
	@Autowired
	private WalletInfo walletInfo;

	public RandomStrategy() {

	}

	public String getName() {
		return name;
	}

	public WalletInfo getWalletInfo() {
		return walletInfo;
	}

	public void setWalletInfo(WalletInfo walletInfo) {
		this.walletInfo = walletInfo;
	}

	public StockBrockerInfo getStockExchangeInfo() {
		return stockExchangeInfo;
	}

	public void setStockExchangeInfo(StockBrockerInfo stockExchangeInfo) {
		this.stockExchangeInfo = stockExchangeInfo;
	}

	@Override
	public List<OrderRequest> listOfRequests() {
		if (walletInfo.walletIsEmpty() || buyOrSell() == 0) {
			return createOrderBuyStocksList(isBuy);
		} else {
			return createOrderSellStocksList(!isBuy);
		}
	}

	private int buyOrSell() {
		Random ran = new Random();
		return ran.nextInt(1);
	}

	private List<QuotationObject> getAllCurrentQuotations() {
		return stockExchangeInfo.getCurrentQuotations();
	}

	private List<OrderRequest> createOrderBuyStocksList(boolean isBuy) {
		List<OrderRequest> orderList = new ArrayList<OrderRequest>();
		for (QuotationObject obj : searchRandomStocksFromStockExchange(getAllCurrentQuotations(), 10)) {
			orderList.add(createRequest(obj.getName(), 20, isBuy, obj.getPrice()));
		}
		return orderList;
	}

	private List<OrderRequest> createOrderSellStocksList(boolean isBuy) {
		List<OrderRequest> orderList = new ArrayList<OrderRequest>();
		for (WalletStock obj : searchRandomStocksFromWallet(walletInfo.getStocksFromWallet())) {
			orderList.add(
					createRequest(obj.getObject().getName(), obj.getQuantity(), isBuy, obj.getObject().getPrice()));
		}
		return orderList;
	}

	private List<QuotationObject> searchRandomStocksFromStockExchange(List<QuotationObject> stocks, int amount) {
		List<QuotationObject> randomStocks = new ArrayList<QuotationObject>();
		Random randomGen = new Random();
		while (amount > 0) {
			int idx = randomGen.nextInt(stocks.size());
			randomStocks.add(stocks.get(idx));
			amount--;
		}
		return randomStocks;
	}

	private List<WalletStock> searchRandomStocksFromWallet(List<WalletStock> stocks) {
		List<WalletStock> randomStocks = new ArrayList<WalletStock>();
		for (int it = 0; it < stocks.size(); it = it + 2) {
			randomStocks.add(stocks.get(it));
		}
		return randomStocks;
	}

	private OrderRequest createRequest(String name, int quantity, boolean isBuy, BigDecimal maxPrice) {
		return new OrderRequest(name, quantity, isBuy, maxPrice);
	}

}
