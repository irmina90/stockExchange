package stockExchange.strategy;

public interface GameStrategyFactory {
	GameStrategy getStrategy(String strategyName);
}
