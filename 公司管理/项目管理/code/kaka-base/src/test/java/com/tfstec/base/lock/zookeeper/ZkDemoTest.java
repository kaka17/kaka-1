package com.tfstec.base.lock.zookeeper;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tfstec.base.lock.zookeeper.ZkDemo;

public class ZkDemoTest {  
     public static void main(String[] args) {  
          int count = 30;  
          CyclicBarrier cyclicBarrier = new CyclicBarrier(count);  
          ExecutorService executorService = Executors.newFixedThreadPool(count);  
          for (int i = 0; i < count; i++)  
               executorService.execute(new ZkDemoTest().new Task(cyclicBarrier));  
  
          executorService.shutdown();  
          while (!executorService.isTerminated()) {  
               try {  
                    Thread.sleep(10);  
               } catch (InterruptedException e) {  
                    e.printStackTrace();  
               }  
          }  
     }  
  
     public class Task implements Runnable {  
          private CyclicBarrier cyclicBarrier;  
  
          public Task(CyclicBarrier cyclicBarrier) {  
               this.cyclicBarrier = cyclicBarrier;  
          }  
  
          @Override  
          public void run() {  
               try {  
                    // 等待所有任务准备就绪  
                    cyclicBarrier.await();  
//                    for(int i=0;i<100;i++){
                    	ZkDemo.execute();
//                    }
                    
                    
               } catch (Exception e) {  
                    e.printStackTrace();  
               }  
          }  
     }  
}  