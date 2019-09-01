package com.hjl.comman.demo;

import com.hjl.constant.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author hjl
 * @Description 线程池demo
 * @Date 2019/8/28 13:34
 */
public class ThreadPoolDemo {
    
    private static final Logger LOG = LogManager.getLogger(ThreadPoolDemo.class);

    public static void main(String[] args) {
        test();
    }

    public static void test(){

        /**
         * LinkedBlockingDeque 不指定大小时默认容量是Integer的最大值
         * 任务数大于corePoolSize且队列容量能放入所有要执行的任务时时多余的任务会放在队列中排队
         * 任务数大于(corePoolSize + 队列容量)时会，多出来的任务数会创建线程来执行,此时执行中的任务总数(不包含队列中等待的)不能大于maximumPoolSize否则会报错
         */
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor(1,20,60L,TimeUnit.SECONDS,new LinkedBlockingDeque<>(1));
        threadPoolExecutor.execute(new TestRunnable("a"));
        threadPoolExecutor.execute(new TestRunnable("b"));
        threadPoolExecutor.execute(new TestRunnable("c"));
        threadPoolExecutor.execute(new TestRunnable("d"));
        try{
            // 等待线程池中所有任务执行完成关闭，但是不会等待待提交的任务完成
            ///threadPoolExecutor.shutdown();
            // 立即终止所有任务并且返回任务列表
            List<Runnable> list = threadPoolExecutor.shutdownNow();
            list.forEach(runnable -> LOG.info("class : {}",runnable.getClass()));
            // 关闭所有线程
            ///threadPoolExecutor.awaitTermination(6,TimeUnit.SECONDS);
        }catch (Exception e){
            LOG.error("close error : ",e);
        }
    }

    /**
     * 获取线程池
     * @param corePoolSize 核心线程数，默认情况下核心线程会一直存活，即使处于闲置状态也不会受存keepAliveTime限制
     * @param maximumPoolSize 线程池所能容纳的最大线程数。超过这个数的线程将被阻塞。当任务队列为没有设置大小的LinkedBlockingDeque时，这个值无效。
     * @param keepAliveTime 非核心线程的闲置超时时间，超过这个时间就会被回收。
     * @param unit 指定keepAliveTime的单位，如TimeUnit.SECONDS。当将allowCoreThreadTimeOut设置为true时对corePoolSize生效。
     * @param workQueue 线程池中的任务队列.常用的有三种队列，SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue
     * @return 线程池
     */
    public static ThreadPoolExecutor getThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, unit,workQueue);
        return threadPoolExecutor;
    }

    static class TestRunnable implements Runnable{

        private String name;

        public TestRunnable(String name) {
            this.name = name;
        }
        /**
         * run
         */
        @Override
        public void run() {
            while(true){
                LOG.info("线程执行中.....当前线程：{},线程名：{}",Thread.currentThread().getName(),this.name);
                try {
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException e) {
                    boolean flag = Thread.interrupted();
                    LOG.info("是否中断成功: {}",flag);
                    LOG.error(Constants.THREAD_POOL_EXECUTOR_EXCEPTION, e);
                }

            }

        }
    }
}
