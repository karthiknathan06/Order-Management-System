package com.company.retailprocessor.config;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageProducer
{
    private Logger logger = LogManager.getLogger(KafkaMessageProducer.class);

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic}")
    String topic;

    /***
     * To Add orderId to kafka broker
     * @param orderData
     */
    @Async
    public void sendMessage(JSONObject orderData)
    {
        logger.log(Level.INFO,"Order Message Kafka Producer: Topic :" +topic+ " Value :"+orderData);
        kafkaTemplate.send(topic, orderData.toString());
    }
}