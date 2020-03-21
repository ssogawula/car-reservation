package com.sobsrental.carreservation.service;

import com.sobsrental.carreservation.domain.Car;
import com.sobsrental.carreservation.exception.ResourceNotFoundException;

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
	 * @return Car object otherwise throws {@link ResourceNotFoundException}
	 */
	Car getCarById(Long id) throws ResourceNotFoundException;
	/**
	 * 
	 * @param carName
	 * @return Car object otherwise throws {@link ResourceNotFoundException}
	 */
	Car	 getCarByCarName(String carName) throws ResourceNotFoundException;
	
	/**
	 * 
	 * @param id TODO
	 * @param car
	 * @return
	 */
	Car updateCar(Long id, Car car);
	
	/**
	 * 
	 * @param car
	 * @return
	 */
	Car updateCar(Car car);
	/**
	 * 
	 * @param id
	 */
	void deleteCar(Long id);
	
}
