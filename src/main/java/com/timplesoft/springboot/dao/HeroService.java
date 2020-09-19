package com.timplesoft.springboot.dao;

import com.timplesoft.springboot.model.Hero;
import com.timplesoft.springboot.model.Power;

import java.util.List;

public interface HeroService {

    List<Hero> findAll();
    Hero findHeroById(int id);
    Hero addHero(Hero hero);
    void deleteHero(int id);

    List<Power> findAllPowerByHeroId(int heroId);
    Power findPowerById(int heroId, int powerId);
    Power addPower(int heroId, Power power);
    void deletePower(int heroId, int powerId);

}
