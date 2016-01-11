package stockExchange.app;

import java.io.IOException;
import java.util.logging.Logger;

public class App {
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());
	
	public static void main(String[] args) throws org.springframework.expression.ParseException, IOException {
		
		Player player = new Player();
		
		LOGGER.info("************** STOCK EXCHANGE **************");

		LOGGER.info("Start balance : " + player.getStartBalance());
		LOGGER.info("Last balance : " + player.getLastBalance());
		LOGGER.info("Stocks balance : " + player.getStocksBalance());
		LOGGER.info("Sum : " + player.getSum());

		LOGGER.info("************** ENDING PROGRAM *****************");

	}
	
}
