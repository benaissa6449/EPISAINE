package edu.ezip.ing1.pds.client.commons;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MapSerializer extends JsonSerializer<Map<String, Object>> {

    @Override
    public void serialize(Map<String, Object> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jsonGenerator.writeStringField(entry.getKey(), String.valueOf(entry.getValue()));
        }
        jsonGenerator.writeEndObject();
    }
}
