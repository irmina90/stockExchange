package stockExchange.brocker;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.stock.StockExchange;
import stockExchange.to.OrderRequest;
import stockExchange.to.QuotationObject;
import stockExchange.wallet.Wallet;

@Component
public class StockBrocker {
	final BigDecimal commisionPercent = new BigDecimal(".005");
	final BigDecimal minCommision = new BigDecimal("5.0");

	@Autowired
	private StockExchange stockExchange;
	@Autowired
	private Wallet wallet;

	public StockBrocker() {

	}

	public void order(List<OrderRequest> requests) {
		for (OrderRequest req : requests) {
			QuotationObject requestQuotation = findCurrentQuotationByName(req.getName());
			buyStock(requestQuotation, req);
			sellStock(requestQuotation, req);
		}
	}

	public void buyStock(QuotationObject requestQuotation, OrderRequest req) {
		if (requestQuotation.getPrice().compareTo(req.getPrice()) <= 0 && req.isBuyOrder() == true) {

			BigDecimal valueOfTransaction = calculateValueOfTransaction(requestQuotation.getPrice(), req.getQuantity());
			if (enoughMoney(
					calculateSumOfValueAndCommision(calculateCommision(valueOfTransaction), valueOfTransaction))) {
				getWallet().addStocks(requestQuotation, req.getQuantity());
				countTransation(requestQuotation.getPrice(), req.getQuantity(), req.isBuyOrder());
			}

		}
	}

	public void sellStock(QuotationObject requestQuotation, OrderRequest req) {
		if (requestQuotation.getPrice().compareTo(req.getPrice()) >= 0 && req.isBuyOrder() == false) {

			BigDecimal valueOfTransaction = calculateValueOfTransaction(requestQuotation.getPrice(), req.getQuantity());

			if (enoughMoney(
					calculateSumOfValueAndCommision(calculateCommision(valueOfTransaction), valueOfTransaction))) {
				getWallet().removeStocks(requestQuotation, req.getQuantity());
				countTransation(requestQuotation.getPrice(), req.getQuantity(), req.isBuyOrder());
			}
		}
	}

	private void countTransation(BigDecimal price, int amount, boolean isBuy) {
		BigDecimal valueOfTransaction = calculateValueOfTransaction(price, amount);
		getWallet().updateWallet(valueOfTransaction, calculateCommision(valueOfTransaction), isBuy);
	}

	private List<QuotationObject> getCurrentQuotations() {
		return this.stockExchange.getCurrentQuotations();
	}

	private QuotationObject findCurrentQuotationByName(String name) {
		for (QuotationObject obj : getCurrentQuotations()) {
			if (obj.getName().equals(name)) {
				return obj;
			}
		}
		return null;
	}

	private boolean enoughMoney(BigDecimal sum) {
		int result = getWallet().getWalletBalance().compareTo(sum);
		if (result >= 0)
			return true;
		else
			return false;
	}

	private BigDecimal calculateSumOfValueAndCommision(BigDecimal commision, BigDecimal valueOfTransaction) {
		BigDecimal result = new BigDecimal(0);
		result = result.add(valueOfTransaction).add(commision);
		return result;
	}

	private BigDecimal calculateValueOfTransaction(BigDecimal price, int amount) {
		return price.multiply(new BigDecimal(String.valueOf(amount)));
	}

	private BigDecimal calculateCommision(BigDecimal valueOfRequest) {
		BigDecimal commision = commisionPercent.multiply(valueOfRequest);
		if (commision.compareTo(minCommision) == 1)
			return commision;
		else
			return minCommision;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public StockExchange getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(StockExchange stockExchange) {
		this.stockExchange = stockExchange;
	}

}
