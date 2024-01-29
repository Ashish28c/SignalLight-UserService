package com.SignalLight.userservice.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SignalLight.userservice.entity.Users;


public interface UserDao extends JpaRepository<Users, Integer>{
	
	Users findByMobilenumber(String Mobilenumber);
	
	@Query(nativeQuery = true,value = "{call getAllUsers()}")
    List<Users> getallusers();
	
	@Query(nativeQuery = true,value = "{call getUserByID(:id)}")
    Users getUserbyId(@Param("id") int id);
	
	

}
