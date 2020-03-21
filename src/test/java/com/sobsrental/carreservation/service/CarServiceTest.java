package com.sobsrental.carreservation.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sobsrental.carreservation.domain.Car;
import com.sobsrental.carreservation.exception.ResourceNotFoundException;
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
	public void getCarByName_ShouldThrowResourceNotFoundException() throws Exception{
		//Arrange
		given(carRepository.findByCarName(anyString())).willReturn(null);
		
		//Assert
		assertThrows(ResourceNotFoundException.class, () -> carService.getCarByCarName("Golf"));
	}
	
	@Test
	public void getCarById_ShouldThrowNullPointerException() {
		//Arrange
		given(carRepository.findById(anyLong())).willReturn(null);
		
		//Assert
		assertThrows(NullPointerException.class, () -> carService.getCarById(0L));
	}
	
	@Test
	public void getCarById_ShouldReturnCarDetails() {
		//Arrange
		given(carRepository.findById(anyLong())).willReturn(Optional.of(new Car(1L, "Toyota", "Bakkie", "White")));
		
		Car existingCar = carService.getCarById(1L);
		//Assert
		assertEquals(existingCar.getCarName(), "Toyota");
		assertEquals(existingCar.getCarType(), "Bakkie");
		assertEquals(existingCar.getColor(), "White");
	}
	
	@Test
	public void updateCar_Test1_ShouldReturnUpdatedCar() {
		//Arrange
		given(carRepository.getOne(1L)).willReturn(new Car(1L, "Toyota", "Bakkie", "White"));
		given(carRepository.save(new Car(1L, "BMW", "HatchBack", "Lite Blue")))
			.willReturn(new Car(1L, "BMW", "HatchBack", "Lite Blue"));
		
		Car updatedCar = carService.updateCar( 1L, new Car("BMW", "HatchBack", "Lite Blue"));
		
		//Assert
		assertEquals(updatedCar.getCarName(), "BMW");
		assertEquals(updatedCar.getCarType(), "HatchBack");
		assertEquals(updatedCar.getColor(), "Lite Blue");
	}
	
	@Test
	public void updateCar_Test2_ShouldReturnUpdatedCar() {
		//Arrange
		given(carRepository.getOne(1L)).willReturn(new Car(1L, "Toyota", "Bakkie", "White"));
		given(carRepository.save(new Car(1L, "BMW", "HatchBack", "Lite Blue")))
			.willReturn(new Car(1L, "BMW", "HatchBack", "Lite Blue"));
		
		Car updatedCar = carService.updateCar(new Car(1L, "BMW", "HatchBack", "Lite Blue"));
		
		//Assert
		assertEquals(updatedCar.getCarName(), "BMW");
		assertEquals(updatedCar.getCarType(), "HatchBack");
		assertEquals(updatedCar.getColor(), "Lite Blue");
	}
	
	@Test
	public void deleteCar_ShouldDeleteExistingCar() {
		//Arrange
		given(carRepository.getOne(1L)).willReturn(new Car(1L, "Toyota", "Bakkie", "White"));
		carService.deleteCar(1L);
		
		//Assert
		assertThrows(ResourceNotFoundException.class, () -> carService.getCarById(1L));
	}
}
