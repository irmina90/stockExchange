package stockExchange.mapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.mysql.fabric.xmlrpc.base.Array;

import junit.framework.TestCase;
import stockExchange.entity.Quotation;
import stockExchange.to.QuotationObject;

public class MapperTest extends TestCase {
    
    @Test
    public void testShouldMapEntity() throws IOException, ParseException {
    	//given
    	Quotation entity = new Quotation(1L, "Name", "20150101", new BigDecimal("12.5"));
    	//when
    	QuotationObject obj = Mapper.map(entity);
    	//then
        assertEquals("Name", obj.getName());
        assertEquals(0, obj.getPrice().compareTo(new BigDecimal("12.5")));
    }
    
    @Test
    public void testShouldMapDays() throws IOException, ParseException {
    	//given
    	Quotation entity1 = new Quotation(1L, "Name", "20150102", new BigDecimal("12.5"));
    	Quotation entity2 = new Quotation(2L, "Name", "20150101", new BigDecimal("12.6"));
    	Quotation entity3 = new Quotation(3L, "Name", "20150102", new BigDecimal("12.6"));
    	List<Quotation> list = Arrays.asList(entity1,entity2,entity3);
    	//when
    	List<String> listOfStrings = Mapper.mapDays(list);
    	//then
        assertEquals(2, listOfStrings.size());
        assertEquals("20150101",listOfStrings.get(0));
    }
}
