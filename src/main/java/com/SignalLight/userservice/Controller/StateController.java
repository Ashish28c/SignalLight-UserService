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

import com.SignalLight.userservice.Services.StateService;
import com.SignalLight.userservice.entity.State;

@RestController
@RequestMapping("/API/V1")
@CrossOrigin("*")
public class StateController {
	
	@Autowired
    private StateService stateService;
	
	
	@GetMapping("/state")
	public ResponseEntity<List<State>> getStates() {
		List<State> list = stateService.getAllStates();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);

	} 
	
	@GetMapping("/state/{stateId}")
	public ResponseEntity<Optional<State>> getStatesbyID(@PathVariable("stateId") int stateID) {
		Optional<State> state = stateService.getStateById(stateID);
		if (state == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(state));
	}
	
	
	

}
