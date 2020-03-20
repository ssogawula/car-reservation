package com.sobsrental.carreservation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {

	@Id
	@GeneratedValue
	private Long id;
	
	private String carName;
	
	private String color;
	
	private String carType;
	
	//Added for JPA, but why?
	public Car() {	}
	
	public Car(String carName, String carType) {
		this.carName = carName;
		this.carType = carType;
	}

	public String getCarName() {
		return carName;
	}
	
	public void setCarName(String carName) {
		this.carName = carName;
	}
	
	public String getCarType() {
		return carType;
	}
	
	
	public void setCarType(String carType) {
		this.carType = carType;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
}
