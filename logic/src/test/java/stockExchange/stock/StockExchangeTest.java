package stockExchange.stock;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "StockExchangeTest-context.xml")
public class StockExchangeTest {

	@Autowired
	StockExchange stockExchange;

	@Test
	public void testShouldSetFirstData() throws IOException, ParseException {
		stockExchange.setFirstData();
		
		assertEquals(3, stockExchange.getDays().size());
		assertEquals(0, stockExchange.getCurrentDayNumber());
		assertEquals("20011024", stockExchange.getCurrentDay());
		assertEquals(2, stockExchange.getCurrentQuotations().size());
	}
	
}
