package com.sobsrental.carreservation.service;

import java.util.List;

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
	List<Car>	 getCarByCarName(String carName) throws ResourceNotFoundException;
	
	/**
	 * 
	 * @param id
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
