package com.allen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

public class ObjectSerializer implements Serializer<Object> {

    private StringSerializer serializer = new StringSerializer();

    @Override
    public byte[] serialize(String topic, Object data) {
        if (data == null) {
            return null;
        }
        try {
            if (data instanceof String) {
                return serializer.serialize(topic, (String) data);
            }
            return serializer.serialize(topic, new ObjectMapper().writeValueAsString(data));
        } catch (JsonProcessingException e) {
            throw new SerializationException(e);
        }
    }

    @Test
    public void main() throws JsonProcessingException {
        byte[] allens =
                new ObjectSerializer().serialize("allen", "ed7a6b28-361a-4237-9f50-2675a1eb0a13");
        Object allen = new ObjectDeserializer().deserialize("allen", allens);
        System.out.println(allen);
    }
}
