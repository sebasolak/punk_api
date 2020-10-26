package punkapi.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import punkapi.demo.dao.BeerDao;
import punkapi.demo.model.Beer;
import punkapi.demo.model.dto.BeerDto;

import java.util.Set;

@Service
public class BeerService {

    String url = "https://api.punkapi.com/v2/beers/";

    private final BeerDao beerDao;

    @Autowired
    public BeerService(@Qualifier("beerDao") BeerDao beerDao) {
        this.beerDao = beerDao;
    }


    public Beer[] getResponse(int num) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Beer[]> response = rest.exchange(url + "?page=" + num + "&per_page=5",
                HttpMethod.GET,
                entity,
                Beer[].class);
        return response.getBody();
    }

    public Beer saveBeer(long beerId, String username) {

        try {
            RestTemplate rest = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("user-agent", "Application");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Beer[]> response = rest.exchange(url + beerId,
                    HttpMethod.GET,
                    entity,
                    Beer[].class);

            String beerName = response.getBody()[0].getName();

            if (isBeerAlreadySaved(beerId, username) == 0) {
                BeerDto beerDto = new BeerDto(beerId, beerName, username);
                beerDao.save(beerDto);
            }

            return response.getBody()[0];
        } catch (HttpClientErrorException e) {
            throw new RuntimeException(String.format("No beer found that matches the ID %d", beerId));
        }
    }

    public Set<String> selectFavouriteBeers(String username) {
        return beerDao.selectFavouriteBeers(username);
    }

    public String deleteBeer(long beerId, String username) {

        if (isBeerAlreadySaved(beerId, username) > 0) {
            String msg = String.format("deleted beer: id= %d, name= %s", beerId, selectBeerNameByHardcodeId(beerId));
            beerDao.deleteBeerFromFavourite(beerId, username);
            return msg;
        }
        return String.format("Beer id %d is incorrect", beerId);
    }

    public int isBeerAlreadySaved(Long beerId, String username) {
        return beerDao.isBeerAlreadySaved(beerId, username);
    }

    public String selectBeerNameByHardcodeId(Long beerId) {
        return beerDao.selectBeerNameByHardcodeId(beerId);
    }

}
