package com.balel.kafka.producer;

import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class MessageSender {

	Properties properties=new Properties();
	Integer numberOfRecords=5;
	
	private void init() throws InterruptedException {
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("kafka.topic.name", "test");
		
		KafkaProducer<String,byte[]> producer=new KafkaProducer<String,byte[]>(this.properties,new StringSerializer(),
				new ByteArraySerializer());
		
		for(int i=0;i<numberOfRecords;i++) {
			byte[] payload=(i+" amar from eclipse"+new Date()).getBytes();
			ProducerRecord<String,byte[]> record=new 
					ProducerRecord<String,byte[]>(properties.getProperty("kafka.topic.name"),payload);
			producer.send(record);
			Thread.sleep(1000);
		}
		
		producer.close();	
	}
	
	public static void main(String [] args) throws InterruptedException {
		
		MessageSender MessageSender=new MessageSender();
		MessageSender.init();
	}
}
