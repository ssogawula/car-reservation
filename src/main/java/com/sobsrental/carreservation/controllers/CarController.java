package com.sobsrental.carreservation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sobsrental.carreservation.domain.Car;
import com.sobsrental.carreservation.exception.CarNotFoundCarException;
import com.sobsrental.carreservation.service.CarService;

@RestController
@RequestMapping("/v1/api/cars")
public class CarController {

	private final CarService carService;
	
	public CarController(CarService carService) {
		this.carService = carService;
	}
	
	public Car AddCar(@RequestBody Car car) {
		
		return null;
	}
	
	@GetMapping("/{carName}")
	public Car getCarByCarName(@PathVariable String carName) {
		
		return carService.getCarByCarName(carName);
	}
	
	public Car updateCar(@RequestBody Car car) {
		
		return null;
	}
	
	public void deleteCar(Long id) {
		
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void carNotFoundException(CarNotFoundCarException e) {
		
	}
}
