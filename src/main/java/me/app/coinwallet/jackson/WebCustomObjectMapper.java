package me.app.coinwallet.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import me.app.coinwallet.entity.Chart;
import me.app.coinwallet.entity.MarketCap;

public class WebCustomObjectMapper extends ObjectMapper {
    public WebCustomObjectMapper() {
//        setDateFormat(new SimpleDateFormat(CoInDateUtil.JS_SERIALIZE_DATE_FORMAT));
        registerModule(new CustomModule());
    }

    /**
     * Customize module for project.
     */
    public static class CustomModule extends SimpleModule {
        public CustomModule() {
            super("ElcaCustomModule", Version.unknownVersion());
            addDeserializer(Chart.class, new ChartDeserialize());
            addDeserializer(MarketCap.class, new MarketCapDeserialize());
            addDeserializer(TrendJson.class,new TrendDeserialize());
            addDeserializer(ExchangeRateJson.class, new ExchangeRateDeserialize());
        }
    }
}
