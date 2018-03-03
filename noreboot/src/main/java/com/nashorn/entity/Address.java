package com.nashorn.entity;


import lombok.Data;

/**
 *
 * @author i324779
 * @date 07/02/2018
 * also can use Google AutoValue framework.
 * refer to:
 * https://github.com/google/auto
 * https://github.com/google/auto/tree/master/value
 * usage:
 * https://github.com/google/auto/tree/master/value
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
