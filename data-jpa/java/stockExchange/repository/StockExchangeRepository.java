package stockExchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import stockExchange.entity.Quotation;

@Repository
public interface StockExchangeRepository extends JpaRepository<Quotation, Long> {
	
    @Query("select quotation from Quotation quotation where upper(quotation.day) like concat(upper(:day), '%')")
    public List<Quotation> findQuotationsByDay(@Param("day") String day);

    @Query("select quotation from Quotation quotation where upper(quotation.name) like concat(upper(:name), '%')")
    public List<Quotation> findQuotationsByName(@Param("name") String name);

}
