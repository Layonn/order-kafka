import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/*
@consumer: bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic order_manager_new_order --from-beginn
**/

public class NewOrder {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, String>(getProperties());
        var messageValue = "00001,layonn.alves@gmail.com";
        var record = new ProducerRecord("order_manager_new_order", messageValue, messageValue);
        producer.send(record, (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.println("Topic: " + data.topic() + "\nPartition: " + data.partition() + "\nOffset: " + data.offset() + "\nTimestamp: " + data.timestamp() );
        }).get();
    }

    private static Properties getProperties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
