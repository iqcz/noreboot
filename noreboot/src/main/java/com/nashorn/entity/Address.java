package com.nashorn.entity;


import lombok.Data;

/**
 *
 * @author i324779
 * @date 07/02/2018
 * also can use Google AutoValue framework.
 */
@Data
public class Address {

    private Integer addressId;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
}
