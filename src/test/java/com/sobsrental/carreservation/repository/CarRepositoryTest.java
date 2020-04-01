package com.sobsrental.carreservation.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		List<Car> cars = carRepository.findByCarName("Toyota");
		
		assertEquals(!cars.isEmpty(), cars.size() > 0);
		Car car = cars.get(0);
		assertEquals(car.getCarName(), "Toyota");
		assertEquals(car.getCarType(), "Bakkie");
		assertEquals(car.getColor(), "White");
	}
	
	@Test
	public void getCarByName_ShouldThrowCarNotFoundException() {
		assertEquals(carRepository.findByCarName("Golf"), new ArrayList<Car>());
	}
}
