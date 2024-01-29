package com.SignalLight.userservice.Services;


import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.SignalLight.userservice.Repositories.PremiseDao;
import com.SignalLight.userservice.entity.Premise;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PremiseService {
	
	
	@Value("${spring.datasource.username}")
	private String user;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String url;

	@Autowired
	private PremiseDao premiseDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public List<Premise> getAll()
	{
		return premiseDao.findAll();
	}
	
	
	public Premise addPremise(Premise premise){
		premise.setUpdateDateTime(new Date());
        premise.setCreateDateTime(new Date());
		return premiseDao.save(premise);
	}
	
	
	public Premise updatePremise(Premise premise,int id){	
		premise.setId(id);
		Date u = getPremiseById(id).get().getCreateDateTime();
		premise.setCreateDateTime(u);
		premise.setUpdateDateTime(new Date());		
		return premiseDao.save(premise);
	} 
	
	
	public Optional<Premise> getPremiseById(int id){	
		Optional<Premise> p=premiseDao.findById(id); 
		return p;
	}
	
	
	public void deletePremise(int id) {
		premiseDao.deleteById(id);
	}
	
		
	public static String readScript() throws IOException {
		File file = ResourceUtils.getFile("classpath:signallight.sql");
		return FileUtils.readFileToString(file);
	}
	
	
	public static void runScript(JdbcTemplate newJdbcTemplate) throws IOException {
		String script = readScript();
		StringBuilder builder = new StringBuilder();
		String[] lines = script.split("\n");

		String delimiter = ";";
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];

			if (line.contains("DELIMITER")) {
				String[] tokens = line.split(" ");
				delimiter = tokens[1];
				builder = new StringBuilder();
				continue;
			}

			if (line.contains(delimiter)) {
				builder.append(line.replace(delimiter, ""));
				newJdbcTemplate.execute(builder.toString());
				builder = new StringBuilder();
			} else {
				builder.append(line);
			}
		}
	}
	
	private String getUrl(String dbName) {
		return url.substring(0, url.lastIndexOf("/") + 1) + dbName + "?autoReconnect=true";
	}

	public DataSource setupDataSource(String dbName) {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass(driverClassName);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		cpds.setJdbcUrl(getUrl(dbName));
		cpds.setUser(user);
		cpds.setPassword(password);
		cpds.setMinPoolSize(10);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(10);
		cpds.setTestConnectionOnCheckout(true);
		return cpds;
	}
	
	public void createDataBase(String dbName) {
		try {
			jdbcTemplate.execute("create database " + dbName);
			DataSource dataSource = setupDataSource(dbName);
			runScript(new JdbcTemplate(dataSource));
			
			log.info("{} database created successfully", dbName);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
