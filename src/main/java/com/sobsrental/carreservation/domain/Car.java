package com.sobsrental.carreservation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {

	@Id
	@GeneratedValue
	private Long id;
	
	private String carName;
	private String carType;
	private String color;
	
	public Car(String carName, String carType, String color) {
		this(null, carName, carType, color);
	}
}
