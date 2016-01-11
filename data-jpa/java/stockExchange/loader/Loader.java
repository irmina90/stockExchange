package stockExchange.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import stockExchange.entity.Quotation;
import stockExchange.repository.StockExchangeRepository;

@Component
public class Loader {
	private String source;
	@Autowired
	private StockExchangeRepository repository;

	public Loader() {

	}

	public void loadData() throws IOException, ParseException {
		try {
			File file = new File(getSource());
			Scanner scanner = new Scanner(file);
			Long id = new Long(1);

			while (scanner.hasNext()) {
				String[] fields = scanner.next().split(",");
				getRepository().save(new Quotation(id, fields[0], fields[1], new BigDecimal(fields[2])));
				id = new Long(id.longValue() + 1);
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public StockExchangeRepository getRepository() {
		return repository;
	}

	public void setRepository(StockExchangeRepository repository) {
		this.repository = repository;
	}


}
