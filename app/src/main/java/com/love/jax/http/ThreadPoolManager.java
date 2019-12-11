package com.love.jax.http;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * com.love.jax.http
 * Created by jax on 2019-12-03 19:12
 * TODO:网络请求线程池
 */
public class ThreadPoolManager {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    // We want at least 2 threads and at most 4 threads in the core pool,
    // preferring to have 1 less than the CPU count to avoid saturating
    // the CPU with background work
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;


    private static ThreadPoolManager instance = new ThreadPoolManager();

    //阻塞方式队列，生产者消费者模式，维护一个请求队列
    private LinkedBlockingQueue<Future<?>> taskQuene = new LinkedBlockingQueue<>();

    private ThreadPoolExecutor threadPoolExecutor;

    public static ThreadPoolManager getInstance() {
        return instance;
    }

    private ThreadPoolManager() {
        Log.i("jax", "ThreadPoolManager: CORE_POOL_SIZE=" + CORE_POOL_SIZE + ",MAXIMUM_POOL_SIZE "
                + "= " + MAXIMUM_POOL_SIZE);
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(MAXIMUM_POOL_SIZE), handler);
        threadPoolExecutor.execute(runable);
    }

    public <T> boolean removeTask(FutureTask futureTask) {
        boolean result = false;
        /**
         * 阻塞式队列是否含有线程
         */
        if (taskQuene.contains(futureTask)) {
            result =  taskQuene.remove(futureTask);
        } else {
            result = threadPoolExecutor.remove(futureTask);
        }
        return result;
    }

    //采用一个死循环的阻塞方式的线程，不断从队列中拿到HTTPTask任务并执行
    private Runnable runable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                FutureTask futrueTask = null;
                try {
                    /**
                     * 阻塞式函数，没有取到futrueTask，任务就阻塞在这里
                     */
                    Log.i("jax", "等待队列大小= " + taskQuene.size());
                    futrueTask = (FutureTask) taskQuene.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (futrueTask != null) {
                    threadPoolExecutor.execute(futrueTask);
                }
                Log.i("jax", "线程池大小=" + threadPoolExecutor.getPoolSize());
            }
        }
    };

    public <T> void execte(FutureTask<T> futureTask) throws InterruptedException {
        taskQuene.put(futureTask);
    }

    //无法执行的任务，通过该方式，继续加入到队列中，放置一次请求接口较多，超过线程池的开销
    private RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                taskQuene.put(new FutureTask<Object>(r, null) {
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
