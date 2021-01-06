package be.vdab.muziek.controllers;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.services.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
        service.findById(id).ifPresent(album -> {
            modelAndView.addObject("album", album);
            modelAndView.addObject("tracks", album.getTracks());
            modelAndView.addObject("totaletracktijd", album.calculateTotaleTrackTime());
        });
        return modelAndView;
    }

    @PostMapping
    public String scoreWijzigen(@Valid Album album, Errors errors){
        if (errors.hasErrors()){
            return "redirect:/albums/" + album.getId();
        }
        service.wijzigScore(album.getId(), album.getScore());
        return "redirect:/albums/" + album.getId();
    }
}
