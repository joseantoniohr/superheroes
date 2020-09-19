package com.timplesoft.springboot.dao;

import com.timplesoft.springboot.model.Hero;
import com.timplesoft.springboot.model.HeroNotFoundException;
import com.timplesoft.springboot.model.Power;
import com.timplesoft.springboot.repository.HeroRepository;
import com.timplesoft.springboot.repository.PowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("jpa")
public class HeroJpaService  implements HeroService {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private PowerRepository powerRepository;

    @Override
    public List<Hero> findAll() {
        return heroRepository.findAll();
    }

    @Override
    public Hero findHeroById(int id) {
        return heroRepository.findById(id).orElse(null);
    }

    @Override
    public Hero addHero(Hero hero) {
        return heroRepository.save(hero);
    }

    @Override
    public void deleteHero(int id) {
        Hero hero = heroRepository.findById(id).orElse(null);
        if ( hero == null ) {
            throw new HeroNotFoundException("The hero with ID " + id + " does not exits.");
        }
        heroRepository.delete(hero);
    }

    @Override
    public List<Power> findAllPowerByHeroId(int heroId) {
        Hero hero = heroRepository.findById(heroId).orElse(null);
        if ( hero != null ) {
            return hero.getPowers();
        }
        return null;
    }

    @Override
    public Power findPowerById(int heroId, int powerId) {
        Hero hero = heroRepository.findById(heroId).orElse(null);
        if ( hero != null && hero.getPowers() != null && !hero.getPowers().isEmpty() ) {
            return hero.getPowers()
                    .stream()
                    .filter(power -> power.getId() == powerId)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @Override
    public Power addPower(int heroId, Power power) {
        Hero hero = heroRepository.findById(heroId).orElse(null);
        if ( hero != null ) {
            power.setHero(hero);
            return powerRepository.save(power);
        }
        return null;
    }

    @Override
    public void deletePower(int heroId, int powerId) {
        Power power = findPowerById(heroId, powerId);
        if ( power != null ) {
            powerRepository.delete(power);
        }
    }

}
