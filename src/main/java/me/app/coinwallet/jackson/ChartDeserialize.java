package me.app.coinwallet.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import me.app.coinwallet.entity.Chart;

import java.io.IOException;

public class ChartDeserialize extends StdDeserializer<Chart> {

    protected ChartDeserialize(Class<?> vc) {
        super(vc);
    }

    public ChartDeserialize(){
        this(null);
    }

    @Override
    public Chart deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Chart chart = new Chart();
        JsonNode listNode = node.get("prices");
        StringBuilder builder = new StringBuilder();
        listNode.forEach(pair-> builder.append(pair.get(0)).append(":").append(pair.get(1)).append(","));
        builder.deleteCharAt(builder.length()-1);
        chart.setPointList(builder.toString());
        return chart;
    }
}
