# CryptoCoins-WebService


* Developed a java web service REST API using Spring Boot by following best practices which provides the following 2 resource end points -
	* cryptocoins/getCoins - returns list of 4 crypto coins
	* cryptocoins/getCoinDetails/{coinId} - returns the details of the coin for requested coin id
	
* Web service ingest all available data from RapidAPI.

* Only returns the data needed (in the UI) back to the client.

* Following modules are present in the application - 
	* com.springrest.springrest.Contollers
	* com.springrest.springrest.Models
	* com.springrest.springrest.Services
	* com.springrest.springrest.ServiceImpl
	* com.springrest.springrest.UtilClass
	
* Following RapidAPI is used to fetch the coin details -
	* https://coinranking1.p.rapidapi.com/
	
* Java stream api is used to map and filter the fetch data into the list.

