package stockExchange.app;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.ParseException;

public class Player {
	private static ApplicationContext context;
	private static final BigDecimal startBalance = new BigDecimal(10000.0);
	static String[] springConfig = { "spring/applicationContext.xml", "spring/databaseContext.xml" };
	private static Beans game;

	
	Player() throws ParseException, IOException{
		context = new ClassPathXmlApplicationContext(springConfig);
		game = context.getBean(Beans.class);
		
		game.getLoader().loadData();
		game.getStockExchange().setFirstData();
		game.getWallet().setStartWalletBalance(startBalance);
		
		while(!game.getStockExchange().noMoreDays()) {
			game.getBrocker().order(game.getWallet().getStrategy().listOfRequests());	
		}
	}
	
	public BigDecimal getStartBalance(){
		return game.getWallet().getStartWalletBalance();
	}

	public BigDecimal getLastBalance(){
		return game.getWallet().getWalletBalance();
	}

	public BigDecimal getStocksBalance(){
		return game.getWallet().getStocksBalance();
	}
	
	public BigDecimal getSum(){
		return game.getWallet().getSumWallet();
	}
}
