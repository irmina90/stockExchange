package stockExchange.wallet;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.to.QuotationObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "WalletTest-context.xml")
public class WalletTest {

	@Autowired
	Wallet wallet;
	
	@Test
	public void testShouldReturnBalance() {
		wallet.addStocks(new QuotationObject("ABC", new BigDecimal(10.0)), 10);
		wallet.addStocks(new QuotationObject("BCD", new BigDecimal(50.0)), 50);
		wallet.removeStocks(new QuotationObject("ABC", new BigDecimal(50.0)), 4);

		assertEquals(2, wallet.getStocks().size());
		assertEquals(6, wallet.getStocks().get(0).getQuantity());
		assertEquals(50, wallet.getStocks().get(1).getQuantity());
		assertEquals(0, wallet.getStocksBalance().compareTo(new BigDecimal(2560.0)));
	}
	
	
}
