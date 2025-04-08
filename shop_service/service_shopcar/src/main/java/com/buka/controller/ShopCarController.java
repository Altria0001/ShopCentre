package com.buka.controller;

import com.buka.domain.AddCarDTO;
import com.buka.entity.R;
import com.buka.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("car")
public class ShopCarController {

	@Autowired
	private ShopCarService shopCarService;

	@RequestMapping("add")
	public R addcar(AddCarDTO addCarDTO){
		return shopCarService.addCar(addCarDTO);
	}
}
