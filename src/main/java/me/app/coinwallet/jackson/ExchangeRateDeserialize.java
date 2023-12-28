package me.app.coinwallet.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import me.app.coinwallet.entity.ExchangeRate;
import me.app.coinwallet.entity.MarketCap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExchangeRateDeserialize extends StdDeserializer<ExchangeRateJson> {

    protected ExchangeRateDeserialize(Class<?> vc) {
        super(vc);
    }

    public ExchangeRateDeserialize(){
        this(null);
    }

    @Override
    public ExchangeRateJson deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        ExchangeRateJson rateJson = new ExchangeRateJson();
        JsonNode rates=node.get("rates");
        Iterator<String> currencies = rates.fieldNames();
        List<ExchangeRate> exchangeRates = new ArrayList<>(50);
        while (currencies.hasNext()){
            JsonNode each = rates.get(currencies.next());
            String name = each.get("name").textValue();
            String unit = each.get("unit").textValue();
            Double value = each.get("value").asDouble();
            exchangeRates.add(new ExchangeRate(name,unit, value));
        }
        rateJson.exchangeRates = exchangeRates;
        return rateJson;
    }
}
