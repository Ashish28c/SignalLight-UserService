package com.SignalLight.userservice.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SignalLight.userservice.Services.UsersService;
import com.SignalLight.userservice.entity.Users;

@RestController
@RequestMapping("/user/API/V1")
@CrossOrigin("*")
public class UserController {

	@Autowired()
	private UsersService usersService;

	@GetMapping("/users")
	public ResponseEntity<List<Users>> getUsers() {
		List<Users> list = usersService.getAllUsers();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);

	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<Users> getuser(@PathVariable("userId") int userID) {
		Users user = usersService.getUserByIDProcedure(userID);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(user);

	}

	@PostMapping("/addusers")
	public ResponseEntity<Users> addUser(@RequestBody Users user) {
	
		try {
			this.usersService.addUser(user); 
			return ResponseEntity.of(Optional.of(user));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

	}

	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<Users> deleteUser(@PathVariable("userId") int userId) {
		try {
			this.usersService.deleteUser(userId);
			return ResponseEntity.ok().build(); 
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/updateuser/{userId}")
	public ResponseEntity<Users> updateUser(@RequestBody Users user, @PathVariable("userId") int userId) {
		try {
			
			this.usersService.updateUser(user, userId);
			return ResponseEntity.ok().build();
		} catch (Exception e) { 
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}  
	}
	
	

}
