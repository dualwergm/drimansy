package com.dg.drimansy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dg.drimansy.model.Car;
import com.dg.drimansy.repository.ICarRepository;
import com.dg.drimansy.view.vo.CarVO;

@Service
public class CarService {
	private final ICarRepository carRepository;
	
	public CarService(ICarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	public List<CarVO> getCarsVO() {
		List<Car> cars = this.carRepository.findAllOrdered();
		return getCarsVO(cars);
	}
	
	@Transactional(readOnly = true)
	public List<Car> findAllOrdered() {
		return this.carRepository.findAllOrdered();
	}
	
	public List<CarVO> getCarsVO(List<Car> cars){
		List<CarVO> carsVO = new ArrayList<CarVO>();
		for(Car car: cars) {
			CarVO pVO = new CarVO();
			pVO.toVO(car);
			carsVO.add(pVO);
		}
		return carsVO;
	}
}
