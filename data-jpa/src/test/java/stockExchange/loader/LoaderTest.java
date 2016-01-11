package stockExchange.loader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.repository.StockExchangeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "LoaderTest-context.xml")
public class LoaderTest {
	@Autowired
	private Loader loader;
	@Autowired
	private StockExchangeRepository repository;

	@Test
	public void testShouldReturnExampleFilePath() throws IOException, ParseException {
		loader.loadData();
		
		assertNotNull(loader.getSource());
		assertEquals(6,repository.findAll().size());
	}
	
}
