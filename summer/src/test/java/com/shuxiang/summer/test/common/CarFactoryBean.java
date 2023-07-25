package com.shuxiang.summer.test.common;

import com.shuxiang.summer.beans.factory.FactoryBean;
import com.shuxiang.summer.test.bean.Car;

public class CarFactoryBean implements FactoryBean<Car> {

	private String brand;

	@Override
	public Car getObject() throws Exception {
		Car car = new Car();
		car.setBrand(brand);
		return car;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}