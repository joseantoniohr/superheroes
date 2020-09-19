package com.timplesoft.springboot.controller;

import com.timplesoft.springboot.dao.HeroService;
import com.timplesoft.springboot.model.Hero;
import com.timplesoft.springboot.model.HeroNotFoundException;
import com.timplesoft.springboot.model.Power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class HeroController {

    @Autowired
    @Qualifier("jpa")
    private HeroService heroService;

    @GetMapping("/hero")
    public List<Hero> findAllHeroes() {
        return heroService.findAll();
    }

    @GetMapping("/hero/{id}")
    public Hero findHeroById(@PathVariable int id) {
        Hero hero = heroService.findHeroById(id);
        if ( hero == null ) {
            throw new HeroNotFoundException("The hero with ID " + id + " does not exits.");
        }
        return hero;
    }

    @PostMapping("/hero")
    public ResponseEntity<Object> addHero(@RequestBody @Valid Hero hero) {
        Hero addedHero = heroService.addHero(hero);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(addedHero.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/hero/{id}")
    public void deleteHeroById(@PathVariable int id) {
        heroService.deleteHero(id);
    }

    @GetMapping("/hero/{heroId}/power")
    public List<Power> findAllPowersByHeroId(@PathVariable int heroId) {
        return heroService.findAllPowerByHeroId(heroId);
    }

    @GetMapping("/hero/{heroId}/power/{powerId}")
    public Power findPowerById(@PathVariable int heroId, @PathVariable int powerId) {
        return heroService.findPowerById(heroId, powerId);
    }

    @PostMapping("/hero/{heroId}/power")
    public ResponseEntity<Object> addPower(@PathVariable int heroId, @RequestBody @Valid Power power) {
        Power newPower = heroService.addPower(heroId, power);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{powerId}")
                .buildAndExpand(newPower.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/hero/{heroId}/power/{powerId}")
    public void deletePowerById(@PathVariable int heroId, @PathVariable int powerId) {
        heroService.deletePower(heroId, powerId);
    }

}
