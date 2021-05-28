package com.springrest.springrest.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//import com.springrest.springrest.Services.IVoteDAO;
import com.springrest.springrest.Models.CryptoCoin;
import com.springrest.springrest.Services.CryptoCoinService;
import com.springrest.springrest.Services.CryptoCoinDAOService;

@Service
public class CryptoCoinServiceImpl implements CryptoCoinService{
	
	@Autowired
	private CryptoCoinDAOService cryptoCoinDAOService;
	
	@Override
	public List<CryptoCoin> getCoins() {
		return cryptoCoinDAOService.getCoins();				
	}
	
	@Override
	public CryptoCoin getCoinDetails(int coinId) {
		return cryptoCoinDAOService.getCoinDetails(coinId);
	}
	
}
