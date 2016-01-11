package stockExchange.brocker;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.loader.Loader;
import stockExchange.stock.StockExchange;
import stockExchange.to.OrderRequest;
import stockExchange.wallet.Wallet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "StockBrockerTest-context.xml")
public class StockBrockerTest {

	@Autowired
	Loader loader;
	@Autowired
	Wallet wallet;
	@Autowired
	StockExchange stockExchange;
	@Autowired
	StockBrocker brocker;

	@Test
	public void testShouldReturnBalances() throws ParseException, IOException {
		loader.loadData();
		stockExchange.setFirstData();
		wallet.setBalanceWallet(new BigDecimal(10000.0));
		
		List<OrderRequest> buyRequests = new ArrayList<OrderRequest>(Arrays.asList(
				new OrderRequest("INTEL", 10, true, new BigDecimal(12.0)),
				new OrderRequest("MICROSOFT", 20, true, new BigDecimal(25.0))));
		brocker.order(buyRequests);
		
		
		List<OrderRequest> sellRequests = new ArrayList<OrderRequest>(Arrays.asList(
				new OrderRequest("INTEL", 5, false, new BigDecimal(10.0)),
				new OrderRequest("MICROSOFT", 5, false, new BigDecimal(10.0))));
		brocker.order(sellRequests);
		System.out.println(wallet.getWalletBalance());
		assertEquals(0, wallet.getStocksBalance().compareTo(new BigDecimal(350.0)));
		assertEquals(0, wallet.getWalletBalance().compareTo(new BigDecimal(9620.0)));
	}

	
}
