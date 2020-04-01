package com.sobsrental.carreservation.model;

import com.sobsrental.carreservation.domain.Car;

public abstract class AbstractCar {
	
	private CarType model;
	
	public AbstractCar(CarType model) {
		this.model = model;
	}
	
	public CarType getModel() {
		return model;
	}
	
	public abstract AbstractCar construct(Car car);
		

	enum CarType {
		LUXURY, SEDAN, SMALL
	}
}
