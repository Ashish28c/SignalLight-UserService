package com.SignalLight.userservice.entity;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="premises") 
public class Premise {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="imagepath")
	private String imagepath;
	
	@Column(name="areaid")
	private int areaId;
	
	@Column(name="cityid")
	private int cityId;
	
	@Column(name="stateid")
	private int stateId;
	
	@Column(name = "isactive")
	boolean isactive;
	
	@Column(name = "createdatetime") 
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date createDateTime;
	
	@Column(name = "updatedatetime")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date updateDateTime;
	
	@Column(name="tag")
	private String tag;
	
	
}