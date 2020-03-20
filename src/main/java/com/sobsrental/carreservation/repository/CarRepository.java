package com.sobsrental.carreservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sobsrental.carreservation.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

	Car findByCarName(String carName);
	
	List<Car>  findByCarType(String carType);
}
