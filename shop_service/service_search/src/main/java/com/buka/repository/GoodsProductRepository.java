package com.buka.repository;

import com.buka.domain.GoodsProductForEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsProductRepository extends ElasticsearchRepository<GoodsProductForEs,String> {
}
