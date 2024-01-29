package com.SignalLight.userservice.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SignalLight.userservice.Services.PremiseService;
import com.SignalLight.userservice.entity.Premise;
import com.SignalLight.userservice.entity.Users;


@RestController
@RequestMapping("/user/API/V1")
@CrossOrigin("*")
public class PremiseController {

	@Autowired
	private PremiseService premiseService;
	

 
	
	@GetMapping("/premise")
	public ResponseEntity<List<Premise>> getUsers() {
		List<Premise> list = premiseService.getAll();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	} 
	
	@GetMapping("/premise/{premiseId}")
	public ResponseEntity<Optional<Premise>> getPremiseById(@PathVariable("premiseId") int premiseId)
	{
		Optional<Premise> premise = premiseService.getPremiseById(premiseId);
		if(premise==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(premise);
	}
	
	
	    @PostMapping("/addPremise")
	    @Transactional
	    public ResponseEntity<Premise> addPremise(@RequestBody Premise premise) {
	        try {
	            
	            String dbname = "SignalLight_"+premise.getName();
	            premise.setTag(dbname);     
	            premiseService.addPremise(premise);
	            premiseService.createDataBase(dbname);         
	            return ResponseEntity.ok(premise);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	    
	    @PutMapping("/updatePremise/{premiseId}")
		public ResponseEntity<Users> updateUser(@RequestBody Premise premise, @PathVariable("premiseId") int premiseId) {
			try {
				
				this.premiseService.updatePremise(premise, premiseId);
				return ResponseEntity.ok().build();
			} catch (Exception e) { 
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

			}  
		}
	 
	   	
	@DeleteMapping("/deletePremise/{premiseId}") 
	public ResponseEntity<String> deletePremise(@PathVariable("premiseId") int premiseId){
		premiseService.deletePremise(premiseId);
		return  ResponseEntity.ok("deleted"); 
	}
}
