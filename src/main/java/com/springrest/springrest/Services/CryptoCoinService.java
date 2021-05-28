package com.springrest.springrest.Services;

import java.util.List;

import com.springrest.springrest.Models.CryptoCoin;

public interface CryptoCoinService {
	
	public List<CryptoCoin> getCoins();
	public CryptoCoin getCoinDetails(int coinId);
	
}
