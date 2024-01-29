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

import com.SignalLight.userservice.Services.AreaService;
import com.SignalLight.userservice.entity.Area;

@RestController
@RequestMapping("/user/API/V1")
@CrossOrigin("*")
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	

	@GetMapping("/area")
	public ResponseEntity<List<Area>> getAreas() {
		List<Area> list = areaService.getAllAreas();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);

	}
	
	@GetMapping("/area/{areaId}")
	public ResponseEntity<Optional<Area>> getAreaByID(@PathVariable("areaId") int areaId) {
		Optional<Area> area = areaService.getAreaById(areaId);
		if (area == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(area));
	}
	
	
	

	

}
