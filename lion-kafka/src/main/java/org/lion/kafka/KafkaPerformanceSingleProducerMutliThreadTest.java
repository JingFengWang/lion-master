package org.lion.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaPerformanceSingleProducerMutliThreadTest {
    public static Logger LOG = LoggerFactory.getLogger(KafkaPerformanceSingleProducerMutliThreadTest.class);
    public KafkaPerformanceSingleProducerMutliThreadTest() {
    }

    private static class Send implements Runnable {
        public static Logger LOG = LoggerFactory.getLogger(Send.class);
        private int times;
        private int partition;
        private String topic;
        private CountDownLatch countDownLatch;
        private SingleKafkaProducer<Integer, String> producer = SingleKafkaProducer.getInstance();
//        private static String content = "总结来说，由于服务中大量使用了Cache，所以堆大小开到了22G。GC算法使用CMS（UseConcMarkSweepGC），开启了降低标记停顿（CMSParallelRemarkEnabled），设置年轻代为并行收集（UseParNewGC），年轻代和老年代的比例为1:2 （NewRatio＝2）.";
        private static String content = "总结 来说 由于 服务中 大量 使用 了 Cache , how dou you du";
        public Send(int times, int partition, String topic, CountDownLatch countDownLatch) {
            this.times = times;
            this.partition = partition;
            this.topic = topic;
            this.countDownLatch = countDownLatch;
        }
//        @Override
//        public void run() {
//            try {
//                for (int i = 0; i < times; i++) {
//                    Future<RecordMetadata> future = producer.send(new ProducerRecord<Integer, String>(topic, partition, partition, i + " | " + content));
//                    RecordMetadata rm = future.get();
//                    System.out.println(topic +" | "+ partition + " "+ rm.partition() + " | " + i + " " +  content);
//                }
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            producer.close();
//        }
//    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            producer.send(new ProducerRecord<Integer, String>(topic, partition, partition, content));
//            System.out.println(topic +" | "+ partition + " | " + i + " " + Thread.currentThread().getName() + " " + content);
        }
        countDownLatch.countDown();
    }
}

    public static void main(String[] args) throws Exception {
        String topic = "pyspark";
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(32);
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));

        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));

        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));

        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));

        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));

        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));

        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));

        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));
        threadPoolExecutor.execute(new Send(1000000, 0, topic, countDownLatch));

        countDownLatch.await();
        threadPoolExecutor.shutdown();
        SingleKafkaProducer.getInstance().close();

    }
}
