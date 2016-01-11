package stockExchange.repository;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.entity.Quotation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "RepositoryTest-context.xml")
public class RepositoryTest {
	
	@Autowired
    private StockExchangeRepository repository;

    @Test
    public void testShouldFindQuotationByName() throws ParseException, IOException {
    	//given
    	List<Quotation> list = repository.findQuotationsByName("INTEL");
    	//when
        assertEquals("INTEL", list.get(0).getName());
    }
    
    @Test
    public void testShouldFindQuotationByDay() throws ParseException, IOException {
    	//given
    	List<Quotation> list = repository.findQuotationsByDay("20011024");
    	//when
    	int size = list.size();
    	//then
        assertEquals(2, size);
    }
    

}
