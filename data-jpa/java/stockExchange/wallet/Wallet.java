package stockExchange.wallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.strategy.Strategy;
import stockExchange.to.QuotationObject;
import stockExchange.to.WalletStock;

@Component
public class Wallet {
	private List<WalletStock> stocks = new ArrayList<WalletStock>();
	private BigDecimal walletBalance;
	private BigDecimal startWalletBalance;
	@Autowired
	private Strategy strategy;

	public Wallet() {

	}

	public BigDecimal getWalletBalance() {
		return this.walletBalance;
	}

	public BigDecimal getSumWallet() {
		BigDecimal result = new BigDecimal(0);
		result = result.add(getWalletBalance()).add(calculateStocksBalance());
		return result;
	}

	public void setBalanceWallet(BigDecimal balanceWallet) {
		this.walletBalance = balanceWallet;
	}

	public BigDecimal getStocksBalance() {
		return calculateStocksBalance();
	}

	private BigDecimal calculateStocksBalance() {
		BigDecimal balance = new BigDecimal(0);
		if (!walletIsEmpty()) {
			for (WalletStock stock : getStocks()) {
				balance = balance.add(
						(new BigDecimal(String.valueOf(stock.getQuantity())).multiply(stock.getObject().getPrice())));
			}
		}
		return balance;
	}

	public void updateWallet(BigDecimal value, BigDecimal commision, boolean isBuy) {
		BigDecimal result = new BigDecimal(0);
		if (isBuy) {
			result = result.add(value).add(commision);
			decreaseWalletBalance(result);
		} else {
			result = result.add(value).subtract(commision);
			increaseWalletBalance(result);
		}
	}

	public List<WalletStock> getStocks() {
		return stocks;
	}

	public boolean walletIsEmpty() {
		return this.stocks == null;
	}

	public void addStocks(QuotationObject requestQuotation, int quantity) {
		stocks.add(new WalletStock(requestQuotation, quantity));
	}

	public void removeStocks(QuotationObject requestQuotation, int quantity) {
		List<WalletStock> listStockToRemove = new ArrayList<WalletStock>();
		for (Integer it = 0; it < stocks.size(); it++) {
			if (stocks.get(it).getObject().getName().equals(requestQuotation.getName())) {
				if (stocks.get(it).getQuantity() <= quantity) {
					listStockToRemove.add(stocks.get(it));
					quantity = quantity - stocks.get(it).getQuantity();
				} else if (stocks.get(it).getQuantity() > quantity) {
					stocks.get(it).setQuantity(stocks.get(it).getQuantity() - quantity);
				}
			}
		}
		removeStocksInWallet(listStockToRemove);
	}

	private void removeStocksInWallet(List<WalletStock> listOfStocks) {
		for (WalletStock stock : listOfStocks) {
			stocks.remove(stock);
		}
	}

	public void decreaseWalletBalance(BigDecimal value) {
		this.walletBalance = this.walletBalance.subtract(value);
	}

	public void increaseWalletBalance(BigDecimal value) {
		this.walletBalance = this.walletBalance.add(value);
	}

	public BigDecimal getStartWalletBalance() {
		return startWalletBalance;
	}

	public void setStartWalletBalance(BigDecimal startWalletBalance) {
		this.startWalletBalance = startWalletBalance;
		this.walletBalance = startWalletBalance;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}


}
