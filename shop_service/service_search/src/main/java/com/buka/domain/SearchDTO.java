package com.buka.domain;


import lombok.Data;

@Data
public class SearchDTO {

    private String name;


    private String dec;  //描述

    private Double minPrice;


    private Double maxPrice;


    private String type;

}
