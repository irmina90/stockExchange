package stockExchange.strategy;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.loader.Loader;
import stockExchange.stock.StockExchange;
import stockExchange.wallet.Wallet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "StrategyTest-context.xml")
public class StrategyTest {

	@Autowired
	Wallet wallet;
	@Autowired
	Strategy strategy;
	@Autowired
	Loader loader;
	@Autowired
	StockExchange stockExchange;
	
	@Test
	public void testShouldReturnBalance() throws ParseException, IOException {
		loader.loadData();
		stockExchange.setFirstData();
		wallet.setStrategy(strategy);
		System.out.println(wallet.getStocksBalance());
		
	}
	
	
}
