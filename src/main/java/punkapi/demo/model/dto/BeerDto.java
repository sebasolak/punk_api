package punkapi.demo.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BeerDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long beerHardcodeId;
    private String name;
    private String username;

    public BeerDto(Long beerHardcodeId, String name, String username) {
        this.beerHardcodeId = beerHardcodeId;
        this.name = name;
        this.username = username;
    }

    public BeerDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBeerHardcodeId() {
        return beerHardcodeId;
    }

    public void setBeerHardcodeId(Long beerHardcodeId) {
        this.beerHardcodeId = beerHardcodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
