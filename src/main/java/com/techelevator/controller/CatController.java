package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import com.techelevator.services.RestCatPicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }


    @RequestMapping( path = "/cards", method = RequestMethod.GET)
    public List<CatCard> getCards(){

        return catCardDao.list();
    }

    @RequestMapping( path = "/cards/{id}", method = RequestMethod.GET)
    public CatCard getCardByID(@PathVariable int id){
        CatCard newCatCard = catCardDao.get(id);

        return newCatCard;
    }


    @RequestMapping( path = "/cards/random", method = RequestMethod.GET)
    public CatCard randomCatCard(){
        CatPic catPic = catPicService.getPic();
        CatFact catFact = catFactService.getFact();
        CatCard catCard = new CatCard();
        catCard.setCatFact(catFact.getText());
        catCard.setImgUrl(catPic.getFile());
        return catCard;

    }

    @RequestMapping( path = "/cards", method = RequestMethod.POST)
    public boolean saveCatCard(@Valid @RequestBody CatCard catCard){
        return catCardDao.save(catCard);
    }

    @RequestMapping( path = "/cards/{id}", method = RequestMethod.PUT)
    public boolean updateCatCard(@Valid @RequestBody CatCard catCard, @PathVariable int id){
        return catCardDao.update(id, catCard);
    }


    @RequestMapping( path = "/cards/{id}", method = RequestMethod.DELETE)
    public boolean deleteCatCard(@PathVariable int id){
        return catCardDao.delete(id);
    }


}
