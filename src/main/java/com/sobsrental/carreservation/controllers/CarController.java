package com.sobsrental.carreservation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sobsrental.carreservation.domain.Car;
import com.sobsrental.carreservation.service.CarService;

@RestController
@RequestMapping("/v1/api/cars")
public class CarController {

	private final CarService carService;
	
	public CarController(CarService carService) {
		this.carService = carService;
	}
	
	@PostMapping
	public ResponseEntity<Car> AddCar(@RequestBody Car car) {
		Car savedCar = carService.addCar(car);
		return new ResponseEntity<Car>(savedCar,HttpStatus.CREATED);
	}
	
	@GetMapping("/{carName}")
	public Car getCarByCarName(@PathVariable String carName) {
		
		return carService.getCarByCarName(carName);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Car> updateCar(@PathVariable Long id,@RequestBody Car car) {
		Car updatedCar = carService.updateCar(id, car);
		return new ResponseEntity<Car>(updatedCar, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Car> updateCar(@RequestBody Car car) {
		Car updatedCar = carService.updateCar(car);
		return new ResponseEntity<Car>(updatedCar, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCar(Long id) {
		carService.deleteCar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
