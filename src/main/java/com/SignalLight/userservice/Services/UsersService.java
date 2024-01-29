package com.SignalLight.userservice.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SignalLight.userservice.Repositories.UserDao;
import com.SignalLight.userservice.entity.Users;

@Component
public class UsersService {
	
	@Autowired
	private UserDao userDao;
	
	
	
	public List<Users> getAllUsers() {
		List<Users> list = (List<Users>) userDao.findAll();
		return list;
	}
	
	public Users addUser(Users user) {
		user.setCreateDateTime(new Date());
		user.setUpdateDateTimeDate(new Date());
		return userDao.save(user);
	}

	public void deleteUser(int id) {
		userDao.deleteById(id);
	}

	public void updateUser(Users user, int id) {
		user.setId(id);
		Date u  = getUserByID(id).get().getCreateDateTime();
		user.setCreateDateTime(u);
		user.setUpdateDateTimeDate(new Date());
		userDao.save(user);
	}

	public Optional<Users> getUserByID(int userID) {
		return userDao.findById(userID);
	}
	
	public Users getUserByIDProcedure(int id) {
		return userDao.getUserbyId(id);
	}
	
	public Users getUserByMobilenumber(String mobilenumber) {
		return userDao.findByMobilenumber(mobilenumber); 
	} 
	

}
