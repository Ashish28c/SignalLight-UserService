package com.SignalLight.userservice.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SignalLight.userservice.Repositories.StateDao;
import com.SignalLight.userservice.entity.State;

@Service
public class StateService {

	@Autowired
	private StateDao stateDao;
	
	public Optional<State> getStateById(int stateId)
	{
		return stateDao.findById(stateId);
	}
	
	public List<State> getAllStates()
	{
		return stateDao.findAll();
	}
	
}
 