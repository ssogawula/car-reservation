package com.sobsrental.carreservation.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sobsrental.carreservation.domain.Car;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class CarIntegrationTest {

	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getCarByName_ShouldReturnCar() {
		//arrange
		
		//act
		ResponseEntity<Car> response =  restTemplate.getForEntity("/v1/api/cars", Car.class);
		
		//Assert
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody().getCarName(), "Toyota");
		assertEquals(response.getBody().getCarType(), "Bakkie");
	}
	
	@Test
	public void addCar_shouldReturnNewCar() {
		
	}
	
	
	@Test
	public void updateCar_shouldReturn_updatedCar() {
		
	}
}
