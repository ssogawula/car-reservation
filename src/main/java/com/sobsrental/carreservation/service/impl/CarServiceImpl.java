package com.sobsrental.carreservation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sobsrental.carreservation.domain.Car;
import com.sobsrental.carreservation.exception.ResourceNotFoundException;
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
		return carRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Car is not found."));
	}
	
	@Override
	public List<Car> getCarByCarName(String carName) {
		List<Car> cars = carRepository.findByCarName(carName);
		if (cars == null || cars.isEmpty()) {
			throw new ResourceNotFoundException("Car is not found.");
		}
		return cars;
	}
	
	@Override
	public Car updateCar(Long id, Car car) {
		Car existingCar = carRepository.getOne(id);
		
		if (existingCar == null) {
			throw new ResourceNotFoundException("Car is not found.");
		}
		car.setId(id);
		return carRepository.save(car);
	}
	
	@Override
	public Car updateCar(Car car) {
		
		if (car == null) {
			//TO do
		}
		
		if (car != null && car.getId() != null) {
			Car existingCar = carRepository.getOne(car.getId());
			if (existingCar == null) {
				throw new ResourceNotFoundException("Car is not found.");
			}
		}
		return carRepository.save(car);
	}
	
	@Override
	public void deleteCar(Long id) {
		Car existingCar = carRepository.getOne(id);
		if (existingCar == null) {
			throw new ResourceNotFoundException("Car is not found.");
		}
		carRepository.delete(existingCar);
	}

}
