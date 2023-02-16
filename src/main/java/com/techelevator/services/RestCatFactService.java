package com.techelevator.services;

import com.techelevator.model.CatPic;
import org.springframework.stereotype.Component;

import com.techelevator.model.CatFact;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatFactService implements CatFactService {

	@Override
	public CatFact getFact() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		CatFact catFactResponse = restTemplate.getForObject("https://cat-data.netlify.app/api/facts/random", CatFact.class);
		return catFactResponse;

	}

}
