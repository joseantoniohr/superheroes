package com.timplesoft.springboot.controller;

import com.timplesoft.springboot.dao.HeroDaoService;
import com.timplesoft.springboot.model.Hero;
import com.timplesoft.springboot.model.HeroNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class HeroController {

    @Autowired
    private HeroDaoService heroDaoService;

    @GetMapping("/hero")
    public List<Hero> findAllHeroes() {
        return heroDaoService.findAll();
    }

    @GetMapping("/hero/{id}")
    public Hero findHeroById(@PathVariable int id) {
        Hero hero = heroDaoService.findHeroById(id);
        if ( hero == null ) {
            throw new HeroNotFoundException("The hero with ID " + id + " does not exits.");
        }
        return hero;
    }

    @PostMapping("/hero")
    public ResponseEntity<Object> addHero(@RequestBody @Valid Hero hero) {
        Hero addedHero = heroDaoService.addHero(hero);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(addedHero.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/hero/{id}")
    public void deleteHeroById(@PathVariable int id) {
        boolean result = heroDaoService.deleteHero(id);
        if ( !result ) {
            throw new HeroNotFoundException("The hero with ID " + id + " does not exits.");
        }
    }

}
