package dx.leesdy.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

// Singleton
public class LDExecutor {
	private static final int POOL_SIZE = 5;
	private static ScheduledExecutorService ex;
	
	public static ScheduledExecutorService getExecutor() {
		if (ex == null) {
			synchronized(ExecutorService.class) {
				int cpuNums = Runtime.getRuntime().availableProcessors();
				ex = Executors.newScheduledThreadPool(cpuNums * POOL_SIZE);
				return ex;
			}
		} else {
			return ex;
		}
	}
}
