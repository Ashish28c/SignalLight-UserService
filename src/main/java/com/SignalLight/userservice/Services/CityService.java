package com.SignalLight.userservice.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SignalLight.userservice.Repositories.CityDao;
import com.SignalLight.userservice.entity.City;

@Service
public class CityService {

	@Autowired
	private CityDao cityDao;
	
	public List<City> getAllCities()
	{
		return cityDao.findAll();
	}
	
	public Optional<City> getCityById(int id)
	{
		return cityDao.findById(id);
	}
	
}
