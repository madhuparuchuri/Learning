import java.util.*;
import java.util.concurrent.*;

public class Stats {
	public static class StatisticsAggregatorImpl implements StatisticsAggregator {
	    Map<String, List<Double>> stockStore = new ConcurrentHashMap<String, List<Double>>();

		@Override
		public void putNewPrice(String symbol, double price) {
		    
		    List<Double> recceivedStocks = stockStore.get(symbol);
		    if(null == recceivedStocks)
		    {
		        recceivedStocks = new java.util.ArrayList<Double>();
		        List<Double> recceivedStocksExits = stockStore.putIfAbsent(symbol,recceivedStocks);
		        if(null != recceivedStocksExits)
		        {
		        	recceivedStocks = recceivedStocksExits;
		        }
		    }
		    recceivedStocks.add(price);
		}

		@Override
		public double getAveragePrice(String symbol) {
		    List<Double> recceivedStocks = stockStore.get(symbol);
		    if(null == recceivedStocks)
		    {
		        return 0.0;
		    }
		    double total = 0.0;
		    for(double price : recceivedStocks)
		    {
		        total+=price;
		    }
		    return total/recceivedStocks.size();
		// YOUR CODE HERE
		}

		@Override
		public int getTickCount(String symbol) {
		     List<Double> recceivedStocks = stockStore.get(symbol);
		    if(null == recceivedStocks)
		    {
		        return 0;
		    }
		    return recceivedStocks.size();
		// YOUR CODE HERE
		}
	}

	////////////////// DO NOT MODIFY BELOW THIS LINE ///////////////////

	public interface StatisticsAggregator {
		// This is an input. Make note of this price.
		public void putNewPrice(String symbol, double price);

		// Get the average price
		public double getAveragePrice(String symbol);

		// Get the total number of prices recorded
		public int getTickCount(String symbol);
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			final StatisticsAggregator stats = new StatisticsAggregatorImpl();
			final Set<String> symbols = new TreeSet<>();

			String line = scanner.nextLine();
			String[] inputs = line.split(",");
			int threads = Integer.parseInt(inputs[0]);
			ExecutorService pool = Executors.newFixedThreadPool(threads);
			for (int i = 1; i < inputs.length; ++i) {
				String[] tokens = inputs[i].split(" ");
				final String symbol = tokens[0];
				symbols.add(symbol);
				final double price = Double.parseDouble(tokens[1]);
				pool.submit(new Runnable() {
					@Override
					public void run() {
						stats.putNewPrice(symbol, price);
					}
				});

			}
			pool.shutdown();
			try {
				pool.awaitTermination(5000, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (String symbol : symbols) {
				System.out.println(String.format("%s %.4f %d", symbol,
						stats.getAveragePrice(symbol),
						stats.getTickCount(symbol)));
			}
		}
		scanner.close();

	}
}