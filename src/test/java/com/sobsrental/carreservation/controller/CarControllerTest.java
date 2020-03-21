package com.sobsrental.carreservation.controller;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sobsrental.carreservation.controllers.CarController;
import com.sobsrental.carreservation.domain.Car;
import com.sobsrental.carreservation.exception.ResourceNotFoundException;
import com.sobsrental.carreservation.service.CarService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private CarService carService;
	
	@Test
	public void carAdd_ShouldReturnNewAddedCar() throws Exception {
		given(carService.addCar(new Car("Toyota", "Bakie", "White"))).willReturn(new Car(1L, "Toyota", "Bakkie", "White"));
		mockMvc.perform(post("/v1/api/cars")
				.content(asJsonString(new Car("Toyota", "Bakkie", "White")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	

	@Test
	public void getCarByCarName_ShouldReturnCarDetails() throws Exception {
		//Arrange 
		given(carService.getCarByCarName(anyString())).willReturn(new Car("Toyota", "Bakkie", "White"));

		//Assert mock
		mockMvc.perform(get("/v1/api/cars/Toyota"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("carName").value("Toyota"))
			.andExpect(jsonPath("carType").value("Bakkie"));
	}
	
	@Test
	public void getCarByCarName_notFound() throws Exception {
		//Arrange
		given(carService.getCarByCarName(anyString())).willThrow(new ResourceNotFoundException("Car is not found."));
		
		//Assert mock
		mockMvc.perform(get("/v1/api/cars/golf"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void getCarById_ShouldReturnCar() throws Exception {
		//Arrange
		given(carService.getCarById(1L)).willReturn(new Car(1L, "Toyota", "Bakkie", "White"));
		
		//Assert Mock
		mockMvc.perform(get("/v1/api/cars/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void updateCar_PathVariable_ShouldReturn_UpatedCar() throws Exception {
		//Arrange
		given(carService.updateCar(1L, new Car("Toyota", "Bakkie", "Silver Gray")))
			.willReturn(new Car(1L, "Toyota", "Bakkie", "Silver Gray"));
		
		//Assert Mock
		mockMvc.perform(put("/v1/api/cars/1")
				.content(asJsonString(new Car("Toyota", "Bakkie", "Silver Gray")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void updateCar_ShouldReturn_UpatedCar() throws Exception {
		//Arrange
		given(carService.updateCar(new Car("Toyota", "Bakkie", "Silver Gray")))
			.willReturn(new Car(1L, "Toyota", "Bakkie", "Silver Gray"));
		
		//Assert Mock
		mockMvc.perform(put("/v1/api/cars")
				.content(asJsonString(new Car(1L,"Toyota", "Bakkie", "Silver Gray")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void deleteCar_ShouldReturnNoContent() throws Exception {
		mockMvc.perform(delete("/v1/api/cars/1")).andExpect(status().isNoContent());
	}
	
	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
