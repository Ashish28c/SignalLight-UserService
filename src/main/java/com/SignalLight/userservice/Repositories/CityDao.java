package com.SignalLight.userservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SignalLight.userservice.entity.City;

public interface CityDao extends JpaRepository<City, Integer>{

}
