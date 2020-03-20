package com.sobsrental.carreservation.service;

import com.sobsrental.carreservation.domain.Car;
import com.sobsrental.carreservation.exception.CarNotFoundCarException;

public interface CarService {
	/**
	 * 
	 * @param car
	 * @return new created car
	 */
	Car addCar(Car car);
	/**
	 * 
	 * @param id - used to query database record;
	 * @return Car object otherwise throws {@link CarNotFoundCarException}
	 */
	Car getCarById(Long id) throws CarNotFoundCarException;
	/**
	 * 
	 * @param carName
	 * @return Car object otherwise throws {@link CarNotFoundCarException}
	 */
	Car	 getCarByCarName(String carName) throws CarNotFoundCarException;
	
}
