package stockExchange.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.repository.StockExchangeRepository;
import stockExchange.to.QuotationObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "ServiceTest-context.xml")
public class ServiceTest {

	@Autowired
	StockExchangeRepository repository;
	@Autowired
	StockExchangeService service;

	@Test
	public void testShouldFindQuotationsByName() throws IOException, ParseException {
		// given
		// when
		List<QuotationObject> listByName = service.findQuotationByName("INTEL");
		// then
		assertEquals(3, listByName.size());
		assertEquals("INTEL", listByName.get(0).getName());
	}

	@Test
	public void testShouldFindQuotationsByDay() throws IOException, ParseException {
		// given
		// when
		List<QuotationObject> listByDay = service.findQuotationByDay("20011024");
		// then
		assertEquals(2, listByDay.size());
	}

	@Test
	public void testShouldFindAllDays() throws IOException, ParseException {
		// given
		// when
		List<String> days = service.getAllDays();
		// then
		assertEquals(3, days.size());
		assertEquals("20011024", days.get(0));
	}

}
