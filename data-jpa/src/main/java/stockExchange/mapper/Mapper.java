package stockExchange.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import stockExchange.entity.Quotation;
import stockExchange.to.QuotationObject;

public class Mapper {
    public static QuotationObject map(Quotation quotationEntity) {
        if (quotationEntity != null) {
            return new QuotationObject(quotationEntity.getName(), quotationEntity.getPrice());
        }
        return null;
    }

    public static List<QuotationObject> map2To(List<Quotation> quotationEntities) {
    	List<QuotationObject> list = new ArrayList<QuotationObject>();
    	for(Quotation q : quotationEntities) {
    		list.add(Mapper.map(q));
    	}
    	return list;
    }
    
    public static List<String> mapDays(List<Quotation> quotationEntities) {
    	Set<String> days = new HashSet<String>();
		List<String> listOfDays = new ArrayList<String>();
		for(Quotation q : quotationEntities){
			days.add(q.getDay());
		}
		listOfDays.addAll(days);
		Collections.sort(listOfDays);
		return listOfDays;
    }

}