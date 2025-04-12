package com.buka.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buka.Ad;
import com.buka.mapper.AdMapper;
import com.buka.service.AdService;
import org.springframework.stereotype.Service;

/**
* @author Altria
* @description 针对表【ad(广告表)】的数据库操作Service实现
* @createDate 2025-02-20 20:05:38
*/
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad>
    implements AdService {

}




