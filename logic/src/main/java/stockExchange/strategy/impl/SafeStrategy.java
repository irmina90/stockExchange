package stockExchange.strategy.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.brocker.StockBrockerInfo;
import stockExchange.strategy.GameStrategy;
import stockExchange.to.OrderRequest;
import stockExchange.to.QuotationObject;
import stockExchange.to.WalletStock;
import stockExchange.wallet.WalletInfo;

@Component
public class SafeStrategy implements GameStrategy {
	private final String name = "SAFE STRATEGY";
	private final boolean isBuy = true;
	private final int minValue = 5;
	private final int midValue = 10;
	private final int maxValue = 20;
	private final BigDecimal five = new BigDecimal(2.0);
	@Autowired
	private StockBrockerInfo stockExchangeInfo;
	@Autowired
	private WalletInfo walletInfo;

	public SafeStrategy() {

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
		if (getWalletInfo().walletIsEmpty() || lotOfMoney()) {
			return createOrderStocksList(searchStocksFromStockExchange(getAmount(getAllCurrentQuotations().size())),
					isBuy);
		} else {
			return createOrderStocksList(
					searchStocksFromWallet(getAmount(getWalletInfo().getStocksFromWallet().size())), !isBuy);
		}
	}

	private boolean lotOfMoney() {
		BigDecimal walletBalance = getWalletInfo().getStartWalletBalance();
		BigDecimal part = walletBalance.divide(five);
		return walletBalance.compareTo(part) >= 0;
	}

	private List<QuotationObject> getAllCurrentQuotations() {
		return getStockExchangeInfo().getCurrentQuotations();
	}

	private int getAmount(int size) {
		if (size < 10)
			return minValue;
		else if (size > 10 && size < 50)
			return midValue;
		return maxValue;
	}

	private int getAmount(BigDecimal price) {
		if (price.compareTo(new BigDecimal(50)) <= 0)
			return 100;
		else
			return 50;
	}

	private List<OrderRequest> createOrderStocksList(List<QuotationObject> stocks, boolean isBuy) {
		List<OrderRequest> orderList = new ArrayList<OrderRequest>();
		for (QuotationObject obj : stocks) {
			orderList.add(createRequest(obj.getName(), getAmount(obj.getPrice()), isBuy, obj.getPrice()));
		}
		return orderList;
	}

	private List<QuotationObject> searchStocksFromStockExchange(int amount) {
		List<QuotationObject> stocksToBuy = new ArrayList<QuotationObject>();
		for (QuotationObject obj : getAllCurrentQuotations()) {
			if (obj.getPrice().compareTo(new BigDecimal(midValue)) <= 0) {
				stocksToBuy.add(new QuotationObject(obj.getName(), obj.getPrice()));
			}
		}
		return stocksToBuy;
	}

	private List<QuotationObject> searchStocksFromWallet(int amount) {
		List<QuotationObject> stocksToSell = new ArrayList<QuotationObject>();
		for (WalletStock obj : getWalletInfo().getStocksFromWallet()) {
			BigDecimal priceOnStockExchange = getPriceByName(obj.getObject().getName());
			if (obj.getObject().getPrice().compareTo(priceOnStockExchange) <= 0) {
				stocksToSell.add(new QuotationObject(obj.getObject().getName(), getPriceByName(name)));
			}
		}
		return stocksToSell;
	}

	private BigDecimal getPriceByName(String name) {
		for (QuotationObject stock : getAllCurrentQuotations()) {
			if (stock.getName().equals(name)) {
				return stock.getPrice();
			}
		}
		return new BigDecimal(0);
	}

	private OrderRequest createRequest(String name, int amount, boolean isBuy, BigDecimal maxPrice) {
		return new OrderRequest(name, amount, isBuy, maxPrice);
	}

}
