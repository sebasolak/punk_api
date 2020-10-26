package punkapi.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import punkapi.demo.service.BeerService;
import punkapi.demo.model.Beer;
import punkapi.demo.model.dto.UserDto;
import punkapi.demo.service.MailService;
import punkapi.demo.service.SecurityContextHolderService;
import punkapi.demo.service.UserService;

import javax.mail.MessagingException;
import java.util.Set;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final BeerService beerService;
    private final SecurityContextHolderService contextHolderService;
    private final MailService mailService;


    @Autowired
    public UserController(UserService userService,
                          BeerService punkApi,
                          SecurityContextHolderService contextHolderService,
                          MailService mailService) {
        this.userService = userService;
        this.beerService = punkApi;
        this.contextHolderService = contextHolderService;
        this.mailService = mailService;
    }

    @GetMapping(path = "{num}")
    public Beer[] pageView(@PathVariable int num) {
        return beerService.getResponse(num);

    }

    @GetMapping("save/{beerId}")
    public Beer saveBeer(@PathVariable("beerId") long beerId) {
        return beerService.saveBeer(beerId, contextHolderService.userUsername());
    }

    @GetMapping("delete/{beerId}")
    public String deleteBeer(@PathVariable("beerId") long beerId) {
        return beerService.deleteBeer(beerId, contextHolderService.userUsername());
    }

    @GetMapping
    public String loginPage() {
        return "success login " + contextHolderService.userUsername();
    }

    @PostMapping("registration")
    public void registerUser(@RequestBody UserDto userDto) {
        userService.save(userDto);
    }

    @GetMapping("favourite")
    public Set<String> favouriteBeers() {
        return beerService.selectFavouriteBeers(contextHolderService.userUsername());
    }

    @GetMapping("sendMail")
    public String sendMail() {
        try {
            mailService.sendMail(
                    userService.selectUserEmailByLogin(contextHolderService.userUsername()),
                    "Favourite beer(s)",
                    beerService.selectFavouriteBeers(contextHolderService.userUsername()).toString(),
                    true
            );

            return "email was send";
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
