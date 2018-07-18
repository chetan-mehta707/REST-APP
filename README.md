# Bitcoin Price Summary (Mean and Median API)
We have designed two REST API's for getting the average and median price of the bitcoin for a mentioned duration.

### API Overview :  
1.  Mean : Will return the JSON containing mean of buy and sale price since the duration(in minutes).
    http://localhost:8080/BitcoinSummary/btc/mean/{duration} .  
    eg : http://localhost:8080/BitcoinSummary/btc/mean/10 .   
    Sample Output : {"currency":"USD","sellPrice":"7480.18","buyPrice":"7491.44"}
          
2.  Median : Will return the JSON containing median of buy and sale price since the duration(in minutes).
    http://localhost:8080/BitcoinSummary/btc/median/{duration} .  
    eg : http://localhost:8080/BitcoinSummary/btc/median/10 .   
    Sample Output : {"currency":"USD","sellPrice":"7473.52","buyPrice":"7487.71"}

### API Design : 
REST API Library  : Jersey .  
Build Tool : Maven .  
Server : Apache Tomcat 8.5 .  
JDK : 1.8 .  
XML Binding : JAXB .  
JSON BInding : jersey-json .  
Base URI : localhost:8080/BitcoinSummary/btc/ .  

API for getting Bitcoin price : https://api.gemini.com/v1/trades/btcusd?since={time} .    This API will return a ***JSON array*** of the historical Bitcoin prices of trading(buy and sell) since the ***time*** given as a parameter. The ***time*** parameter takes epoch time as an input. Our API consumes the duration(in minutes) sent as part of request, generates the epoch time from that and feeds it as an input to the above API.

Our Application is divided into four modules(packages) viz: Service, Data, Model and JUnit.
1. ***com.moneytap.service.BitcoinDetailService*** is a Service class containing the two API's.
2. ***com.moneytap.data.BitcoinDetails*** class has the core business logic for calling the gemini API for getting data and manipulating the data.
3. ***com.moneytap.model.BitcoinPrice*** is used for providing the POJO object for JSON and XML binding.
4. ***com.moneytap.junit.BitcoinSummaryUT*** is used for Unit Testing of the two API's.

## Setup ##
Clone the repository.
Go to Eclipse -> File -> Import Projects -> Maven -> Existing Maven Projects -> Specify the path of the cloned directory.
Make sure Maven and Tomcat Server is configured with Eclipse.

## Demo ##
Run the project over a tomcat server. Once the server is started, call the following two URI's from Rest Client or a browser . 
Mean : http://localhost:8080/BitcoinSummary/btc/mean/10 .  
Median : http://localhost:8080/BitcoinSummary/btc/median/10 .  

## Testing ##
We have implemented JUnit test cases for testing the API's in the class ***com.moneytap.junit.BitcoinSummaryUT*** .
For running the Test cases right click on ***BitcoinSummaryUT.java*** and click Run as ***JUnit Test***

## Assumption ##
***duration*** parameter is assumed to be an integer having duration in minutes.
