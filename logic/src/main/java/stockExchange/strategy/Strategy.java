package stockExchange.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.brocker.StockBrockerInfo;
import stockExchange.to.OrderRequest;
import stockExchange.to.QuotationObject;
import stockExchange.to.WalletStock;
import stockExchange.wallet.WalletInfo;

@Component
public class Strategy {
	private final boolean isBuy = true;
	private final int maxAmount = 40;
	private final BigDecimal number = new BigDecimal(2.0);

	@Autowired
	private StockBrockerInfo stockExchangeInfo;
	@Autowired
	private WalletInfo walletInfo;

	public Strategy() {

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

	public List<OrderRequest> listOfRequests() {
		if (getWalletInfo().walletIsEmpty() || lotOfMoney()) {
			return createOrderStocksList(
					searchStocksFromStockExchange(getRandomAmount(getAllCurrentQuotations().size())), isBuy);
		} else {
			return createOrderStocksList(
					searchStocksFromWallet(getRandomAmount(getWalletInfo().getStocksFromWallet().size())), !isBuy);
		}
	}

	private boolean lotOfMoney() {
		BigDecimal walletBalance = getWalletInfo().getStartWalletBalance();
		BigDecimal part = walletBalance.divide(number);
		return walletBalance.compareTo(part) >= 0;
	}

	private List<QuotationObject> getAllCurrentQuotations() {
		return getStockExchangeInfo().getCurrentQuotations();
	}

	private List<OrderRequest> createOrderStocksList(List<QuotationObject> stocks, boolean isBuy) {
		List<OrderRequest> orderList = new ArrayList<OrderRequest>();
		for (QuotationObject obj : stocks) {
			orderList.add(createRequest(obj.getName(), getRandomAmount(maxAmount), isBuy, obj.getPrice()));
		}
		return orderList;
	}

	private List<QuotationObject> searchStocksFromStockExchange(int amount) {
		List<QuotationObject> stocksToBuy = new ArrayList<QuotationObject>();
		for (QuotationObject obj : getAllCurrentQuotations()) {
			stocksToBuy.add(new QuotationObject(obj.getName(), obj.getPrice()));
		}
		return stocksToBuy;
	}

	private List<QuotationObject> searchStocksFromWallet(int amount) {
		List<QuotationObject> stocksToSell = new ArrayList<QuotationObject>();
		for (WalletStock obj : getWalletInfo().getStocksFromWallet()) {
			BigDecimal priceOnStockExchange = getPriceByName(obj.getObject().getName());
			if (obj.getObject().getPrice().compareTo(priceOnStockExchange) <= 0) {
				stocksToSell.add(new QuotationObject(obj.getObject().getName(), priceOnStockExchange));
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

	private int getRandomAmount(int max) {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(max);
	}

}
