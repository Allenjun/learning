package com.allen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ObjectDeserializer implements Deserializer<Object> {

    private StringDeserializer deserializer = new StringDeserializer();

    @Override
    public Object deserialize(String topic, byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        try {
            String deserialize = deserializer.deserialize(topic, data);
            if (!deserialize.startsWith("{") && !deserialize.endsWith("}")) {
                return deserialize;
            }
            return new ObjectMapper().readValue(deserialize, Object.class);
        } catch (JsonProcessingException e) {
            throw new SerializationException(e);
        }
    }
}
