package com.SignalLight.userservice.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SignalLight.userservice.Repositories.AreaDao;
import com.SignalLight.userservice.entity.Area;

@Service
public class AreaService {

	@Autowired
	private AreaDao areaDao;
	
	public List<Area> getAllAreas()
	{
		return areaDao.findAll();
	}
	public Optional<Area> getAreaById(int id)
	{
		return areaDao.findById(id);
	}
}
 