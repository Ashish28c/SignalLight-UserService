package com.SignalLight.userservice.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SignalLight.userservice.Services.CityService;
import com.SignalLight.userservice.entity.City;

@RestController
@RequestMapping("/API/V1")
@CrossOrigin("*")
public class CityController {
	
	
	@Autowired
    private CityService cityService;
	
	
	@GetMapping("/city")
	public ResponseEntity<List<City>> getCities() {
		List<City> list = cityService.getAllCities();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	@GetMapping("/city/{cityId}")
	public ResponseEntity<Optional<City>> getCitybyID(@PathVariable("cityId") int cityID) {
		Optional<City> city = cityService.getCityById(cityID);
		if (city == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(city));
	}
	
	 

}
