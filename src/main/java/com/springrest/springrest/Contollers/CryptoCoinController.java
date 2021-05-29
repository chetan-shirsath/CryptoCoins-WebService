package com.springrest.springrest.Contollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.Models.CryptoCoin;
import com.springrest.springrest.Services.CryptoCoinService;

/**
 * Handles requests for the application home page.
 */
@RestController
public class CryptoCoinController {
	@Autowired
	private CryptoCoinService cryptoCoinService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping(value = "cryptocoins/getCoins", produces = "application/JSON")
	ResponseEntity<List<CryptoCoin>> getCoins() {
		try {
			List<CryptoCoin> output = cryptoCoinService.getCoins();
			
			if(!output.isEmpty())
				return new ResponseEntity<>(output, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "cryptocoins/getCoinDetails/{coinId}", produces = "application/JSON")
	ResponseEntity<CryptoCoin> getCoinDetails(@PathVariable int coinId) {
		try {
				if(coinId <= 0 || coinId > 9) return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
				
				CryptoCoin output = cryptoCoinService.getCoinDetails(coinId);
			
			if(output != null)
				return new ResponseEntity<>(output, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
