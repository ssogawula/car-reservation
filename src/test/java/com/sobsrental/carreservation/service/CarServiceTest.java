package com.sobsrental.carreservation.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sobsrental.carreservation.domain.Car;
import com.sobsrental.carreservation.exception.CarNotFoundCarException;
import com.sobsrental.carreservation.repository.CarRepository;
import com.sobsrental.carreservation.service.impl.CarServiceImpl;

public class CarServiceTest {

	private CarService carService;
	@Mock
	private CarRepository carRepository;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		carService = new CarServiceImpl(carRepository);
	}
	
	@Test
	public void getCarByName_ShouldReturnCarDetails() {
		//Arrange
		given(carRepository.findByCarName(anyString())).willReturn(new Car("Toyota", "Bakkie", "White"));
		//Query car
		Car car = carService.getCarByCarName("Toyota");
		
		//Assert
		assertEquals(car.getCarName(), "Toyota");
		assertEquals(car.getCarType(), "Bakkie");
		assertEquals(car.getColor(), "White");
	}
	
	@Test
	public void getCarByName_ShouldThrowCarNotFoundException() throws Exception{
		//Arrange
		given(carRepository.findByCarName(anyString())).willReturn(null);
		
		//Assert
		assertThrows(CarNotFoundCarException.class, () -> carService.getCarByCarName("Golf"));
	}
}
