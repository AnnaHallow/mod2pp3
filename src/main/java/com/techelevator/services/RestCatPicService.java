package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatPic;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatPicService implements CatPicService {

	@Override
	public CatPic getPic() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		CatPic catPicResponse = restTemplate.getForObject("https://cat-data.netlify.app/api/pictures/random", CatPic.class);
		return catPicResponse;
	}

}	
