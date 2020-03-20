package com.sobsrental.carreservation.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.sobsrental.carreservation.controllers.CarController;
import com.sobsrental.carreservation.domain.Car;
import com.sobsrental.carreservation.exception.CarNotFoundCarException;
import com.sobsrental.carreservation.service.CarService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private CarService carService;
	
	@Test
	public void getCarByCarName_ShouldReturnCarDetails() throws Exception {
		//Arrange 
		given(carService.getCarByCarName(anyString())).willReturn(new Car("Toyota", "Bakkie"));

		//Assert mock
		mockMvc.perform(get("/v1/api/cars/Toyota"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("carName").value("Toyota"))
			.andExpect(jsonPath("carType").value("Bakkie"));
	}
	
	@Test
	public void getCarByCarName_notFound() throws Exception {
		//Arrange
		given(carService.getCarByCarName(anyString())).willThrow(new CarNotFoundCarException());
		
		//Assert mock
		mockMvc.perform(get("/v1/api/cars/golf"))
			.andExpect(status().isNotFound());
	}
}
