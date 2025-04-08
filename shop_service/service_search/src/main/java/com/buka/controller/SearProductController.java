package com.buka.controller;


import com.buka.domain.GoodsProductForEs;
import com.buka.domain.SearchDTO;
import com.buka.entity.R;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearProductController {


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("search")
    public R searchProduct(SearchDTO searchDTO){
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();  //复核查询构建起
        List<GoodsProductForEs> goodsProductForEs=null;
        if (searchDTO.getName()!=null  &&  !searchDTO.getName().equals("")){
            boolQueryBuilder.must(new MatchQueryBuilder("name",searchDTO.getName()));
            boolQueryBuilder.must(new MatchQueryBuilder("description",searchDTO.getDec()));
            boolQueryBuilder.must(new RangeQueryBuilder("price").lt(searchDTO.getMaxPrice()).gt(searchDTO.getMinPrice()));
            boolQueryBuilder.must(new TermQueryBuilder("salesModel",searchDTO.getType()));
            NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).build();
            goodsProductForEs = elasticsearchTemplate.queryForList(build, GoodsProductForEs.class);
            System.out.println(goodsProductForEs);
        }
        System.out.println("123");
        return R.ok(goodsProductForEs);
    }

}
