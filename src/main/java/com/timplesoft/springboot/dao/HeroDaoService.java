package com.timplesoft.springboot.dao;

import com.timplesoft.springboot.model.Hero;
import com.timplesoft.springboot.model.Power;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("dao")
public class HeroDaoService implements HeroService {

    private static List<Hero> heroes = new ArrayList<>();

    static {
        heroes.add(new Hero(1, "Peter Parker", "Spiderman", new Date()));
        heroes.add(new Hero(2, "Tony Stark", "Ironman", new Date()));
        heroes.add(new Hero(3, "Bruce Wayne", "Batman", new Date()));
    }

    private static int counter = 3;

    // Get all heroes
    @Override
    public List<Hero> findAll() {
        return heroes;
    }

    // Retrieve an hero
    @Override
    public Hero findHeroById(int id) {
        Hero result = null;
        for(Hero hero : heroes) {
            if (hero.getId() == id) {
                result = hero;
                break;
            }
        }
        return result;
    }

    // Add an hero
    @Override
    public Hero addHero(Hero hero) {
        hero.setId(++counter);
        heroes.add(hero);
        return hero;
    }

    // Remove an hero
    @Override
    public void deleteHero(int id) {
        Iterator<Hero> heroIterator = heroes.iterator();
        Hero heroToRemove = null;
        do {
            heroToRemove = heroIterator.next();
            if (heroToRemove.getId() == id) {
                heroIterator.remove();
            }
        } while(heroIterator.hasNext());
    }

    @Override
    public List<Power> findAllPowerByHeroId(int heroId) {
        return null;
    }

    @Override
    public Power findPowerById(int heroId, int powerId) {
        return null;
    }

    @Override
    public Power addPower(int heroId, Power power) {
        return null;
    }

    @Override
    public void deletePower(int heroId, int powerId) {

    }

}
