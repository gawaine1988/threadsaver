package com.yp;

import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;
import org.apache.log4j.Logger;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * 线程重启工具使用示例
 * */
public class Example extends ThreadSaver<Example> implements Runnable {
    ExecutorService executorService;

    public Example(ExecutorService executorService) {
        this.executorService = executorService;
        Set(this);
    }

    private static Logger loger = Logger.getLogger("Example");

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.SECONDS.sleep(1);
                throw new RuntimeException("线程崩溃！");
            }
        } catch (Exception e) {
            loger.error(e.getMessage());
        } finally {
            SaveThread(executorService);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Example(executorService));
    }
}
