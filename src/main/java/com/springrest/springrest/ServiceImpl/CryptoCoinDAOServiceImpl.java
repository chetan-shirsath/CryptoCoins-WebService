package com.springrest.springrest.ServiceImpl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.springrest.springrest.Models.CryptoCoin;
import com.springrest.springrest.Services.CryptoCoinDAOService;
import com.springrest.springrest.UtilClass.CryptoCoinsProvider;

@Repository
public class CryptoCoinDAOServiceImpl implements CryptoCoinDAOService{

	@Value("${baseUrl}")
	private String baseUrl;
	
	@Override
	public List<CryptoCoin> getCoins() {	
		
		try {
			JsonObject jsonResponse = getJsonResponse("coins");
			List<CryptoCoin> outList = new ArrayList<CryptoCoin>();
		    if(jsonResponse != null) {
				JsonArray coinsArray = jsonResponse.getAsJsonArray("coins");
				
				coinsArray.forEach((coin) -> {
					JsonObject coin1 = (JsonObject)coin;
					CryptoCoin cryptoCoin = new CryptoCoin();
					cryptoCoin.setId(coin1.get("id").getAsInt());
					cryptoCoin.setName(coin1.get("name").getAsString());
					cryptoCoin.setSymbol(coin1.get("symbol").getAsString());
					cryptoCoin.setVolume(coin1.get("volume").getAsLong());
					cryptoCoin.setPrice(new BigDecimal(coin1.get("price").getAsString()));	
					outList.add(cryptoCoin);
				});
				
				return outList;
		    }
		    return outList;
	
		}catch(Exception e) {
			System.out.println(e);
			throw e;
		}	
	}
	
	@Override
	public CryptoCoin getCoinDetails(int coinId) {
		try {
			JsonObject jsonResponse = getJsonResponse("coin/"+coinId);
			CryptoCoin cryptoCoin = null;
		    if(jsonResponse != null) {
		    	JsonObject coinObject = jsonResponse.getAsJsonObject("coin");
		    	
				cryptoCoin = new CryptoCoin();
				cryptoCoin.setId(coinObject.get("id").getAsInt());
				cryptoCoin.setName(coinObject.get("name").getAsString());
				cryptoCoin.setSymbol(coinObject.get("symbol").getAsString());
				cryptoCoin.setVolume(coinObject.get("volume").getAsLong());
				cryptoCoin.setPrice(new BigDecimal(coinObject.get("price").getAsString()));	
			
				return cryptoCoin;
		    }
		    return cryptoCoin;
	
		}catch(Exception e) {
			System.out.println(e);
			throw e;
		}
	}
	
	private JsonObject getJsonResponse(String uri){
		try {
			
			CryptoCoinsProvider cryptoCoinsProvider = CryptoCoinsProvider.getUserVoteProvider();
		    ResponseEntity<String> response = cryptoCoinsProvider.getRestTemplate()
		    									.exchange(baseUrl+uri, HttpMethod.GET, cryptoCoinsProvider.getHeaderEntity(), String.class);
		    if(response.getStatusCodeValue() == 200) {
		    	JsonObject jsonBody = new JsonParser().parse(response.getBody()).getAsJsonObject();
		    	JsonObject jsonData = jsonBody.getAsJsonObject("data");
		    	return jsonData;
		    }else {
		    	return null;
		    }
		}catch(Exception e) {
			System.out.println(e);
			throw e;
		}
	}
	
}
