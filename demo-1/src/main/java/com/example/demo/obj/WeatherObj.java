package com.example.demo.obj;

import lombok.Data;

@Data
public class WeatherObj {

	private Integer id;

	private Integer location_id;

	private String name;

	private Integer temperature;

	private Integer humidity;

}