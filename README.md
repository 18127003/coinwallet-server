# coinwallet-server
 Server for Coin wallet mobile application 

 Although the application itself doesn't depend on our managed server, this is created to provide the ability to aggregate trending and up-to-date information
 about crypto-currencies to help the user make informed-decisions with their properties.

 This server runs with Spring Boot and provides RESTful API to get the data, using a simple MVC layer design.
 As a front-end user, you can get following data:

 As for this application, it uses the Schedule API to automately collect market data from CoinGecko, our trusted source. 
 The collected data is then saved into PostgreSQL database using Hibernate and will be fetched on demand. Currently, the hosted
 database is connected using API key, which can be configured using the properties files. For the sake of simplicity, no encoding
 was used to encrypt the key.
