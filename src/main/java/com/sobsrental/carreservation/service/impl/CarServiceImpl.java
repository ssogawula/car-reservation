package com.sobsrental.carreservation.service.impl;

import org.springframework.stereotype.Service;

import com.sobsrental.carreservation.domain.Car;
import com.sobsrental.carreservation.exception.CarNotFoundCarException;
import com.sobsrental.carreservation.repository.CarRepository;
import com.sobsrental.carreservation.service.CarService;

@Service
public class CarServiceImpl implements CarService {
	
	private final CarRepository carRepository;
	
	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	@Override
	public Car addCar(Car car) {
		return carRepository.save(car);
	}
	
	@Override
	public Car getCarById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Car getCarByCarName(String carName) {
		Car car = carRepository.findByCarName(carName);
		if (car == null) {
			throw new CarNotFoundCarException();
		}
		return car;
	}

}
