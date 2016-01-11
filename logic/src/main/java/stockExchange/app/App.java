package stockExchange.app;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	private static ApplicationContext context;
	private static final BigDecimal startBalance = new BigDecimal(10000.0);
	
	static String[] springConfig = { "spring/applicationContext.xml", "spring/databaseContext.xml" };

	
	public static void main(String[] args) throws org.springframework.expression.ParseException, IOException {
		context = new ClassPathXmlApplicationContext(springConfig);
		Beans game = context.getBean(Beans.class);
		
		
		System.out.println("************** STOCK EXCHANGE **************");

		game.getLoader().loadData();
		game.getStockExchange().setFirstData();
		game.getWallet().setStartWalletBalance(startBalance);
		
		
		int it = 0;
		while(it < game.getStockExchange().getAmountOfDays()) {
			game.getBrocker().order(game.getWallet().getRiskyStrategy().listOfRequests());
			it++;
		}
		
		System.out.println("Strategy: " + game.getWallet().getRiskyStrategy().getName());
		System.out.println(game.getRepository().count());
		System.out.println("wallet balance: " + game.getWallet().getWalletBalance());
		System.out.println("stocks balance: " + game.getWallet().getStocksBalance());
		System.out.println("SUM: " +  game.getWallet().getSumWallet());


		System.out.println("************** ENDING PROGRAM *****************");

	}
	
	
}
