package com.example.demo.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 *
 * @Author zhourui
 * @Date 2019/10/14 9:27
 */
public class ThreadUtil {

    private static ExecutorService executorService = null;

    public static ExecutorService newThreadPool() {
        return new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2 + 1,
                                      Runtime.getRuntime().availableProcessors() * 2 + 1,
                                      60L,
                                       TimeUnit.MILLISECONDS,
                                       new LinkedBlockingQueue<Runnable>());
    }

    public static void execute(Runnable runnable) {
        executorService.execute(runnable);
    }
}
