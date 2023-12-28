package me.app.coinwallet;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.app.coinwallet.jackson.WebCustomObjectMapper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication(scanBasePackageClasses = WebConfig.class)
@Slf4j
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("PUT","DELETE","GET","POST","PATCH")
                        .allowCredentials(false);
            }
        };
    }

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI("postgresql://usfpxxcuvrstrahx6g1f:XjqlzY59mJ74Skf1F6jkoRBmFDAZqG@bac5nvuyllwv88cfqtbq-postgresql.services.clever-cloud.com:5432/bac5nvuyllwv88cfqtbq");

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }


    @Bean
    public ObjectMapper objectMapper() {
        return new WebCustomObjectMapper();
    }

}
