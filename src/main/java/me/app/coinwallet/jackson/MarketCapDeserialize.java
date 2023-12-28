package me.app.coinwallet.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import me.app.coinwallet.entity.MarketCap;

import java.io.IOException;

public class MarketCapDeserialize extends StdDeserializer<MarketCap> {
    protected MarketCapDeserialize(Class<?> vc) {
        super(vc);
    }

    public MarketCapDeserialize(){
        this(null);
    }
    @Override
    public MarketCap deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        MarketCap cap= new MarketCap();
        cap.setCoinId(node.get("id").textValue());
        cap.setMarketCapValue(node.get("market_cap").asLong());
        cap.setFluctuation(node.get("price_change_percentage_24h").floatValue());
        cap.setSymbol(node.get("symbol").textValue());
        cap.setImage(node.get("image").textValue());
        cap.setName(node.get("name").textValue());
        cap.setCurrentPrice(node.get("current_price").asDouble());
        cap.setHigh(node.get("high_24h").asDouble());
        cap.setLow(node.get("low_24h").asDouble());
        cap.setTotalVolume(node.get("total_volume").asDouble());
        cap.setMarketCapRank(node.get("market_cap_rank").asInt());
        return cap;
    }
}

