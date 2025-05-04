package com.buka.service;

import com.buka.domain.ItbukaSeckill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buka.entity.R;

import java.util.List;

/**
* @author Altria
* @description 针对表【itbuka_seckill(秒杀表)】的数据库操作Service
* @createDate 2025-04-19 14:47:30
*/
public interface ItbukaSeckillService extends IService<ItbukaSeckill> {


	boolean buyGoods(Long productid);

	R slect();


}
