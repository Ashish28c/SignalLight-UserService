package com.SignalLight.userservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SignalLight.userservice.entity.Premise;

public interface PremiseDao extends JpaRepository<Premise, Integer> {

}
