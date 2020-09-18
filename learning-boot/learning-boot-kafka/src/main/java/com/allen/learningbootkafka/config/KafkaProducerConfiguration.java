package com.allen.learningbootkafka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.BatchErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentBatchErrorHandler;
import org.springframework.kafka.support.LoggingProducerListener;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;

/**
 * @author JUN @Description TODO
 * @createTime 16:20
 */
@Configuration
public class KafkaProducerConfiguration {

    @Autowired
    private KafkaProperties properties;

    @Autowired(required = false)
    private RecordMessageConverter messageConverter;

    @Bean
    public ProducerListener<Object, Object> kafkaProducerListener() {
        return new LoggingProducerListener<>();
    }

    @Bean
    public ProducerFactory<Object, Object> kafkaProducerFactory() {
        return new DefaultKafkaProducerFactory<>(this.properties.buildProducerProperties());
    }

    @Bean
    public KafkaTemplate<?, ?> kafkaTemplate() {
        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<>(kafkaProducerFactory());
        if (this.messageConverter != null) {
            kafkaTemplate.setMessageConverter(this.messageConverter);
        }
        kafkaTemplate.setProducerListener(kafkaProducerListener());
        kafkaTemplate.setDefaultTopic(this.properties.getTemplate().getDefaultTopic());
        return kafkaTemplate;
    }

    @Bean
    @Primary
    public ProducerFactory<Object, Object> kafkaProducerInTransactionFactory() {
        DefaultKafkaProducerFactory<Object, Object> factory =
                new DefaultKafkaProducerFactory<>(this.properties.buildProducerProperties());
        String transactionIdPrefix = this.properties.getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        return factory;
    }

    @Bean
    @Primary
    public KafkaTemplate<?, ?> kafkaTemplateInTransaction() {
        KafkaTemplate<Object, Object> kafkaTemplate =
                new KafkaTemplate<>(kafkaProducerInTransactionFactory());
        if (this.messageConverter != null) {
            kafkaTemplate.setMessageConverter(this.messageConverter);
        }
        kafkaTemplate.setProducerListener(kafkaProducerListener());
        kafkaTemplate.setDefaultTopic(this.properties.getTemplate().getDefaultTopic());
        return kafkaTemplate;
    }

  /*@Bean
  public ErrorHandler kafkaErrorHandler(KafkaTemplate<Object, Object> template) {
      BiConsumer<ConsumerRecord<?, ?>, Exception> recoverer = new DeadLetterPublishingRecoverer(template);
      return new SeekToCurrentErrorHandler(recoverer, 3);
  }*/

    @Bean
    public BatchErrorHandler kafkaBatchErrorHandler() {
        return new SeekToCurrentBatchErrorHandler();
    }
}
