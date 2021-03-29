package com.jester.crypto.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jester.crypto.DTO.HistoricalPriceList;
import com.jester.crypto.DTO.Time;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.jackson.JsonObjectSerializer;

@JsonComponent
public class HistoricalPriceJson {

  public static class Deserializer extends JsonObjectDeserializer<HistoricalPriceList> {
    @Override
    protected HistoricalPriceList deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
      JsonNode bpi = tree.get("bpi");
      Iterator<JsonNode> iterator = bpi.iterator();
      Iterator<Map.Entry<String, JsonNode>> nodes = bpi.fields();
      Map<String, Double> map = new HashMap<>();
      HistoricalPriceList historicalPriceList = new HistoricalPriceList();
      while (nodes.hasNext()) {
        Map.Entry<String, JsonNode> entry = nodes.next();
        map.put(entry.getKey(), entry.getValue().asDouble());
      }
      historicalPriceList.setBpi(map);
      historicalPriceList.setDisclaimer(tree.get("disclaimer").asText("No disclaimer present"));
      Time time = new Time();
      time.setUpdated(tree.get("time").get("updated").asText());
      time.setUpdatedISO(tree.get("time").get("updatedISO").asText());
      historicalPriceList.setTime(time);
      return historicalPriceList;
    }
  }

  public static class Serializer extends JsonObjectSerializer<HistoricalPriceList> {

    @Override
    protected void serializeObject(HistoricalPriceList value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
      jgen.writeArrayFieldStart("bpi");
      value.getBpi().forEach((k, v) -> {
        try {
          jgen.writeStartObject();
          jgen.writeStringField(k, String.valueOf(v));
          jgen.writeEndObject();
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
      jgen.writeEndArray();
      jgen.writeStringField("time", value.getTime().toString());
      jgen.writeStringField("disclaimer", value.getDisclaimer());
    }
  }
}
