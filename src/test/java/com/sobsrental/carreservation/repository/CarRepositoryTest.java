package com.sobsrental.carreservation.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.sobsrental.carreservation.domain.Car;

@DataJpaTest
public class CarRepositoryTest {

	@Autowired
	private CarRepository  carRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@BeforeEach
	public void init() {
		testEntityManager.persistAndFlush(new Car("Toyota", "Bakkie", "White"));
	}
	
	@Test
	public void getCarByName_ShouldReturnCarDetails() {
		Car car = carRepository.findByCarName("Toyota");
		
		assertEquals(car.getCarName(), "Toyota");
	}
	
	@Test
	public void getCarByName_ShouldThrowCarNotFoundException() {
		assertEquals(carRepository.findByCarName("Golf"), null);
	}
}
