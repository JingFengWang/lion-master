package org.lion.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

public class SingleKafkaProducer<K, V> extends KafkaProducer {

    private static class Lazy {
        private static final Properties props = new Properties();
        static {
        props.put("bootstrap.servers", "10.11.96.16:9092,10.11.96.15:9091,10.11.133.108:9093");
        props.put("acks", "-1");
        props.put("retries", 0);
        props.put("batch.size", 163840);
        props.put("linger.ms", 5);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        }
        private static final SingleKafkaProducer INSTANCE = new SingleKafkaProducer(props);
    }

    public SingleKafkaProducer(Properties properties) {
        super(properties);
    }

    public static final SingleKafkaProducer getInstance() {
        return Lazy.INSTANCE;
    }
}
