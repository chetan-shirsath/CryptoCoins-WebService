package com.springrest.springrest.UtilClass;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class CryptoCoinsProvider {
	
	private static CryptoCoinsProvider cryptoCoinsProvider = null;
	private RestTemplate restTemplate = null;
	private HttpEntity<String> headerEntity = null;
	
	private CryptoCoinsProvider(RestTemplate restTemplate1) {
		restTemplate = restTemplate1;	  
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.set("x-rapidapi-key", "63fe65c373msh642a24cf5f7570ep1901e1jsnfbcfcef65c97");
	    headers.set("x-rapidapi-host", "coinranking1.p.rapidapi.com");
	 
	    headerEntity = new HttpEntity<String>(headers);
	}
	
	public static CryptoCoinsProvider getUserVoteProvider() {
		if(cryptoCoinsProvider == null) {
			cryptoCoinsProvider = new CryptoCoinsProvider(new RestTemplate());
		}
		return cryptoCoinsProvider;
	}
	
	public RestTemplate getRestTemplate(){
		if(restTemplate != null) {
			return restTemplate;
		}
		return null;
	}
	
	public HttpEntity<String> getHeaderEntity(){
		if(headerEntity != null) {
			return headerEntity;
		}
		return null;
	}
}
