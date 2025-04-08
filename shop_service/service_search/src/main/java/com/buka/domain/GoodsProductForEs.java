package com.buka.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品表
 * @TableName goods_product
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "java_0410_product")
public class GoodsProductForEs implements Serializable {
    /**
     * 商品编号
     */
    @Id
    private Long id;

    /**
     * 商品名称
     */
    @Field(store = true,type = FieldType.Text ,analyzer = "ik_smart" )
    private String name;

    /**
     * 价格
     */
    @Field(index = true, store = true,type = FieldType.Double )
    private BigDecimal price;

    /**
     * 销售模式
     */
    @Field(type = FieldType.Keyword)
    private String salesModel;

    /**
     * 商品类型
     */
    @Field(type = FieldType.Keyword)
    private String type;

    /**
     * 上下架状态(0上架1下架 )
     */
    @Field(type = FieldType.Keyword)
    private Integer status;

    /**
     * 审核状态(0未审核 1通过 2拒绝)
     */
    @Field(type = FieldType.Keyword)
    private Integer audit;

    /**
     * 店铺名称
     */
    @Field(store = true,type = FieldType.Text ,analyzer = "ik_smart" )
    private String shopName;


    /**
     * 商品卖点
     */
    @Field(store = true,type = FieldType.Text ,analyzer = "ik_smart" )
    private String selling;


    /**
     * 商品图片
     */
    private String img;

    /**
     * 商品视频
     */
    private String video;

    /**
     * 商品描述
     */
    @Field(store = true,type = FieldType.Text ,analyzer = "ik_smart" )
    private String description;


}
