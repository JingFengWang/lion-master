package org.lion.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaPerformanceMutliProducerTest {
    public static Logger LOG = LoggerFactory.getLogger(KafkaPerformanceMutliProducerTest.class);
    public KafkaPerformanceMutliProducerTest() {
    }

    private static class Send implements Runnable {
        public static Logger LOG = LoggerFactory.getLogger(Send.class);
        private int times;
        private int partition;
        private String topic;
        private KafkaProducer<Integer, String> producer;
        private static String content = "总结来说，由于服务中大量使用了Cache，所以堆大小开到了22G。GC算法使用CMS（UseConcMarkSweepGC），开启了降低标记停顿（CMSParallelRemarkEnabled），设置年轻代为并行收集（UseParNewGC），年轻代和老年代的比例为1:2 （NewRatio＝2）.";
        public Send(int times, int partition, String topic) {
            Properties props = new Properties();
//            props.put("bootstrap.servers", "10.11.96.16:9092,10.11.96.15:9091,10.11.133.108:9093");
            props.put("bootstrap.servers", "121.14.64.36:9090,121.14.64.47:9091");
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            producer = new KafkaProducer<>(props);
            this.times = times;
            this.partition = partition;
            this.topic = topic;
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
            System.out.println(topic +" | "+ partition + " | " + i + " " + Thread.currentThread().getName() + " " + content);
        }
        producer.close();
    }
}

    public static void main(String[] args) throws Exception {
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(32);
        String topic = "jx3.chat.dx.new";
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        /**
//         *
//         */
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));
//        threadPoolExecutor.execute(new Send(100000000, 0, topic));

        threadPoolExecutor.execute(new Send(1000000, 0, topic));
        threadPoolExecutor.execute(new Send(1000000, 1, topic));
        threadPoolExecutor.execute(new Send(1000000, 2, topic));
        threadPoolExecutor.execute(new Send(1000000, 3, topic));

//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
        /**
         *
         */
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));

//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
//        threadPoolExecutor.execute(new Send(1000000, 0, topic));
        threadPoolExecutor.shutdown();

//        threadPoolExecutor.execute(new Send(100000000, 1, topic));
//        threadPoolExecutor.execute(new Send(100000000, 1, topic));
//        threadPoolExecutor.execute(new Send(100000000, 1, topic));
//        threadPoolExecutor.execute(new Send(100000000, 1, topic));
//
//        threadPoolExecutor.execute(new Send(100000000, 2, topic));
//        threadPoolExecutor.execute(new Send(100000000, 2, topic));
//        threadPoolExecutor.execute(new Send(100000000, 2, topic));
//        threadPoolExecutor.execute(new Send(100000000, 2, topic));
//
//        threadPoolExecutor.execute(new Send(100000000, 3, topic));
//        threadPoolExecutor.execute(new Send(100000000, 3, topic));
//        threadPoolExecutor.execute(new Send(100000000, 3, topic));
//        threadPoolExecutor.execute(new Send(100000000, 3, topic));




    }
}
