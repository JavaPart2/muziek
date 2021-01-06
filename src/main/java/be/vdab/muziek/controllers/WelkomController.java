package be.vdab.muziek.controllers;

import be.vdab.muziek.services.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class WelkomController {
    private final AlbumService service;

    public WelkomController(AlbumService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView welkom(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("albums", service.findAllAlbums());
        return modelAndView;
    }
}
