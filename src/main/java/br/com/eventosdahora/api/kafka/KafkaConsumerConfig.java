package br.com.eventosdahora.api.kafka;

import br.com.eventosdahora.api.controller.request.EmailRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	
	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;
	
	@Value(value = "${kafka.groupId}")
	private String groupId;
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, EmailRequest> pedidoKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, EmailRequest> factory
				= new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(pedidoConsumerFactory());
		return factory;
	}
	
	@Bean
	public ConsumerFactory<String, EmailRequest> pedidoConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		
		JsonDeserializer<EmailRequest> deserializer = new JsonDeserializer<>(EmailRequest.class);
		deserializer.setRemoveTypeHeaders(false);
		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeMapperForKey(true);
		
		
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
	}
}
