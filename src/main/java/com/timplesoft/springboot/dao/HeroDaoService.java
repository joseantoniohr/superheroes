package com.timplesoft.springboot.dao;

import com.timplesoft.springboot.model.Hero;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HeroDaoService {

    private static List<Hero> heroes = new ArrayList<>();

    static {
        heroes.add(new Hero(1, "Peter Parker", "Spiderman", new Date()));
        heroes.add(new Hero(2, "Tony Stark", "Ironman", new Date()));
        heroes.add(new Hero(3, "Bruce Wayne", "Batman", new Date()));
    }

    private static int counter = 3;

    // Get all heroes
    public List<Hero> findAll() {
        return heroes;
    }

    // Retrieve an hero
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
    public Hero addHero(Hero hero) {
        hero.setId(++counter);
        heroes.add(hero);
        return hero;
    }

    // Remove an hero
    public boolean deleteHero(int id) {
        Iterator<Hero> heroIterator = heroes.iterator();
        Hero heroToRemove = null;
        do {
            heroToRemove = heroIterator.next();
            if (heroToRemove.getId() == id) {
                heroIterator.remove();
                return true;
            }
        } while(heroIterator.hasNext());

        return false;
    }

}
