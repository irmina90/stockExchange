package stockExchange.wallet;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.to.WalletStock;

@Component
public class WalletInfo {
	@Autowired
	private Wallet wallet;
	
	public WalletInfo(){
		
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	public BigDecimal getWalletBalance() {
		return this.wallet.getWalletBalance();
	}
	
	public BigDecimal getStartWalletBalance() {
		return this.wallet.getStartWalletBalance();
	}
	
	public boolean walletIsEmpty(){
		return this.wallet.walletIsEmpty();
	}
	
	public List<WalletStock> getStocksFromWallet(){
		return this.wallet.getStocks();
	}
	
}
