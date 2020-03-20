package com.sobsrental.carreservation.service.impl;

import org.springframework.stereotype.Service;

import com.sobsrental.carreservation.domain.Car;
import com.sobsrental.carreservation.repository.CarRepository;
import com.sobsrental.carreservation.service.CarService;

@Service
public class CarServiceImpl implements CarService {
	
	private final CarRepository carRepository;
	
	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	@Override
	public Car getCarByCarName(String carName) {
		return carRepository.findByCarName(carName);
	}

}
