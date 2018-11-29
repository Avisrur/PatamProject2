package threads;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
public class ShortestJobFirst {

    private ExecutorService threadExecutor;
    private ExecutorService threadScheduler = Executors.newSingleThreadExecutor();
    private PriorityBlockingQueue<ClientRunnable> priorityBlockingQueue;

    public static Comparator<ClientRunnable> Comparator = (clientRunnable1, clientRunnable2) -> {
        return clientRunnable1.getBoardSize() - clientRunnable2.getBoardSize();
    };

    public ShortestJobFirst(Integer poolSize, Integer queueSize) {
        threadExecutor = Executors.newFixedThreadPool(poolSize);
        priorityBlockingQueue = new PriorityBlockingQueue<>(queueSize, Comparator);
        threadScheduler.execute(() -> {
            while (true) {
                try {
                    if (!priorityBlockingQueue.isEmpty())
                        threadExecutor.execute(priorityBlockingQueue.take());
                } catch (InterruptedException exception) {
                    System.out.println("ShortestJobFirst.ShortestJobFirst()"+ exception.toString());
                    break;
                }
            }
        });
    }

    public PriorityBlockingQueue<ClientRunnable> getPriorityQueue() {
        return priorityBlockingQueue;
    }

    public void close() {
        threadExecutor.shutdown();
    }
}