package me.app.coinwallet.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import me.app.coinwallet.entity.MarketCap;

import java.io.IOException;

public class TrendDeserialize extends StdDeserializer<TrendJson> {
    protected TrendDeserialize(Class<?> vc) {
        super(vc);
    }

    public TrendDeserialize(){
        this(null);
    }
    @Override
    public TrendJson deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        TrendJson trendJson= new TrendJson();
        JsonNode listNode = node.get("coins");
        listNode.forEach(jsonNode->{
            trendJson.addCoin(jsonNode.get("item").get("id").textValue());
        });
        return trendJson;
    }
}
