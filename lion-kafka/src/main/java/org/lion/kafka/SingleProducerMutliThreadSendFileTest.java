package org.lion.kafka;


import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleProducerMutliThreadSendFileTest {
    public static Logger LOG = LoggerFactory.getLogger(SingleProducerMutliThreadSendFileTest.class);
    public SingleProducerMutliThreadSendFileTest() {
    }

    private static class Send implements Runnable {
        public static Logger LOG = LoggerFactory.getLogger(Send.class);
        private BufferedReader reader;
        private int partition;
        private String topic;
        private CountDownLatch countDownLatch;
        private SingleKafkaProducer<Integer, String> producer = SingleKafkaProducer.getInstance();
//        private static String content = "总结来说，由于服务中大量使用了Cache，所以堆大小开到了22G。GC算法使用CMS（UseConcMarkSweepGC），开启了降低标记停顿（CMSParallelRemarkEnabled），设置年轻代为并行收集（UseParNewGC），年轻代和老年代的比例为1:2 （NewRatio＝2）.";
        private static String content = "总结 来说 由于 服务中 大量 使用 了 Cache , how dou you du";
        public Send(BufferedReader reader, int partition, String topic, CountDownLatch countDownLatch) {
            this.reader = reader;
            this.partition = partition;
            this.topic = topic;
            this.countDownLatch = countDownLatch;
        }


    @Override
    public void run() {
        try {
            String line = null;
            while ((line = reader.readLine()) != null) {
                producer.send(new ProducerRecord<Integer, String>(topic, partition, partition, line));
                System.out.println(topic +" | "+ partition + " | " + Thread.currentThread().getName() + " " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        countDownLatch.countDown();
    }
}

    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();
        File file = null;
        if (args.length == 1) {
            System.out.println(args[0]);
            file = new File(args[0]);
        } else {
            file = new File("F:\\jx3-chat-all-ws.txt");
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Random random = new Random();
        String topic = "jx3.chat.all.ws";
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(64);
        CountDownLatch countDownLatch = new CountDownLatch(32);
        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));


//        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 0, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 1, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 2, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));
//        threadPoolExecutor.execute(new Send(reader, 3, topic, countDownLatch));

        countDownLatch.await();
        threadPoolExecutor.shutdown();
        reader.close();
        SingleKafkaProducer.getInstance().close();
        long end = System.currentTimeMillis();
        long cost = end - begin;
        System.out.println(cost);
    }
}
