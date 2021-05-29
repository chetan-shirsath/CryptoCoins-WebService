package com.springrest.springrest.ServiceImpl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import  java.util.stream.*;
import org.json.*;


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
			JSONObject jsonResponse = getJsonResponse("coins");
			List<CryptoCoin> outList = new ArrayList<CryptoCoin>();
		    if(jsonResponse != null) {
		    	JSONArray coinsArray = jsonResponse.getJSONArray("coins"); 
		    	
		    	outList = coinsArray.toList().stream()
		    			.map(j -> (HashMap<String,Object>)j)
		    			.filter(j -> j.get("symbol").toString().startsWith("B"))
		    			.map(j -> {
							CryptoCoin cryptoCoin = new CryptoCoin();
							cryptoCoin.setId(Integer.parseInt(j.get("id").toString()));
							cryptoCoin.setName(j.get("name").toString());
							cryptoCoin.setSymbol(j.get("symbol").toString());
							cryptoCoin.setVolume(Long.parseLong(j.get("volume").toString()));
							cryptoCoin.setPrice(new BigDecimal(j.get("price").toString()));
							return cryptoCoin;

		    			})
		    			.collect(Collectors.toList());
				
//				coinsArray.forEach((coin) -> {
//					JSONObject coin1 = (JSONObject)coin;
//					CryptoCoin cryptoCoin = new CryptoCoin();
//					cryptoCoin.setId(coin1.getInt("id"));
//					cryptoCoin.setName(coin1.getString("name"));
//					cryptoCoin.setSymbol(coin1.getString("symbol"));
//					cryptoCoin.setVolume(coin1.getLong("volume"));
//					cryptoCoin.setPrice(new BigDecimal(coin1.getString("price")));	
//					outList.add(cryptoCoin);
//				});
				
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
			JSONObject jsonResponse = getJsonResponse("coin/"+coinId);
			CryptoCoin cryptoCoin = null;
		    if(jsonResponse != null) {
		    	JSONObject coinObject = jsonResponse.getJSONObject("coin");
		    	
				cryptoCoin = new CryptoCoin();
				cryptoCoin.setId(coinObject.getInt("id"));
				cryptoCoin.setName(coinObject.getString("name"));
				cryptoCoin.setSymbol(coinObject.getString("symbol"));
				cryptoCoin.setVolume(coinObject.getLong("volume"));
				cryptoCoin.setPrice(new BigDecimal(coinObject.getString("price")));	
			
				return cryptoCoin;
		    }
		    return cryptoCoin;
	
		}catch(Exception e) {
			System.out.println(e);
			throw e;
		}
	}
	
	private JSONObject getJsonResponse(String uri){
		try {
			
			CryptoCoinsProvider cryptoCoinsProvider = CryptoCoinsProvider.getUserVoteProvider();
		    ResponseEntity<String> response = cryptoCoinsProvider.getRestTemplate()
		    									.exchange(baseUrl+uri, HttpMethod.GET, cryptoCoinsProvider.getHeaderEntity(), String.class);
		    if(response.getStatusCodeValue() == 200) {
		    	JSONObject jsonBody = new JSONObject(response.getBody());
		    	JSONObject jsonData = jsonBody.getJSONObject("data");
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
