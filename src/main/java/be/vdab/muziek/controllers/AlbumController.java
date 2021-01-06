package be.vdab.muziek.controllers;

import be.vdab.muziek.services.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("albums")
public class AlbumController {
    private final AlbumService service;

    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ModelAndView album(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("album");
//        modelAndView.addObject("album", service.findById(id));
        service.findById(id).ifPresent(album -> {
            modelAndView.addObject("album", album);
            modelAndView.addObject("tracks", album.getTracks());
            modelAndView.addObject("totaletracktijd", album.calculateTotaleTrackTime());
        });
        return modelAndView;
    }

    @PostMapping
    public ModelAndView scoreWijzigen(){

    }
}
