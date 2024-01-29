package com.SignalLight.userservice.entity;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data	@AllArgsConstructor	@NoArgsConstructor 
@Entity
@Table(name="area")
public class Area {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="cityid")
	private int cityId;
	
	@Column(name="stateid")
	private int stateId;
	
	@Column(name="pincode")
	private int pincode;
	
	@Column(name = "createdatetime")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date createDateTime;
	
	@Column(name = "updatedatetime")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date updateDateTime;

}
