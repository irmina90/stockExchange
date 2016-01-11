package stockExchange.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.brocker.StockBrocker;
import stockExchange.brocker.StockBrockerInfo;
import stockExchange.loader.Loader;
import stockExchange.repository.StockExchangeRepository;
import stockExchange.service.StockExchangeService;
import stockExchange.stock.StockExchange;
import stockExchange.wallet.Wallet;
import stockExchange.wallet.WalletInfo;

@Component
public class Beans {
	@Autowired
	private Loader loader;
	@Autowired
	private StockExchangeRepository repository;
	@Autowired
	private StockExchangeService service;
	@Autowired
	private StockExchange stockExchange;
	@Autowired
	private Wallet Wallet;
	@Autowired
	private StockBrocker brocker;
	@Autowired
	private StockBrockerInfo stockExchangeInfo;
	@Autowired
	private WalletInfo walletInfo;

	public Beans() {

	}

	public Loader getLoader() {
		return loader;
	}

	public void setLoader(Loader loader) {
		this.loader = loader;
	}

	public StockExchangeService getService() {
		return service;
	}

	public void setService(StockExchangeService service) {
		this.service = service;
	}

	public StockExchange getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(StockExchange stockExchange) {
		this.stockExchange = stockExchange;
	}

	public Wallet getWallet() {
		return Wallet;
	}

	public void setWallet(Wallet wallet) {
		Wallet = wallet;
	}

	public StockBrocker getBrocker() {
		return brocker;
	}

	public void setBrocker(StockBrocker brocker) {
		this.brocker = brocker;
	}

	public StockBrockerInfo getStockExchangeInfo() {
		return stockExchangeInfo;
	}

	public void setStockExchangeInfo(StockBrockerInfo stockExchangeInfo) {
		this.stockExchangeInfo = stockExchangeInfo;
	}

	public WalletInfo getWalletInfo() {
		return walletInfo;
	}

	public void setWalletInfo(WalletInfo walletInfo) {
		this.walletInfo = walletInfo;
	}

	public StockExchangeRepository getRepository() {
		return repository;
	}

	public void setRepository(StockExchangeRepository repository) {
		this.repository = repository;
	}


}
