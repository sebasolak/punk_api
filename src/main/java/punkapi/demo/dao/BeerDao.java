package punkapi.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import punkapi.demo.model.dto.BeerDto;

import javax.transaction.Transactional;
import java.util.Set;

@Repository("beerDao")
public interface BeerDao extends JpaRepository<BeerDto, Long> {

    @Query(value = "Select name from beer_dto  WHERE username like ?1", nativeQuery = true)
    Set<String> selectFavouriteBeers(String username);

    @Modifying
    @Transactional
    @Query(value = "Delete from beer_dto WHERE beer_hardcode_id like ?1 and username like ?2", nativeQuery = true)
    void deleteBeerFromFavourite(Long beerId, String username);

    @Query(value = "Select count(*) from beer_dto  WHERE beer_hardcode_id like ?1 and username like ?2", nativeQuery = true)
    int isBeerAlreadySaved(Long beerId, String username);

    @Query(value = "Select distinct name from beer_dto WHERE beer_hardcode_id like ?1", nativeQuery = true)
    String selectBeerNameByHardcodeId(Long beerId);


}
