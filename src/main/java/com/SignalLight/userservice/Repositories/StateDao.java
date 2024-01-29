package com.SignalLight.userservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SignalLight.userservice.entity.State;

public interface StateDao extends JpaRepository<State, Integer> {

}
