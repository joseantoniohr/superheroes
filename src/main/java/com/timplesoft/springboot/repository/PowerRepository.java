package com.timplesoft.springboot.repository;

import com.timplesoft.springboot.model.Power;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PowerRepository extends JpaRepository<Power, Integer> {
}
