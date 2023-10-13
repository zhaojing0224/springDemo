package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Weather;
import com.example.demo.service.WeatherService;

@Controller
public class HelloController {
	@Autowired
	WeatherService weatherService;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping("/hello")
	public String hello(Model model) {

		model.addAttribute("hello", "Hello World!"); // Hello World!の表示
		model.addAttribute("newWeather", new Weather());

		// 気象データの取得
		List<Weather> weatherDataList = weatherService.findAllWeatherData();
		model.addAttribute("weatherDataList", weatherDataList);

		List<Weather> weatherDataTokyo = weatherService.findWetherDataListByName("東京");
		model.addAttribute("weatherDataTokyo", weatherDataTokyo);

		List<Weather> weatherDataNaha = weatherService.findWetherDataListByName("那覇");
		model.addAttribute("weatherDataNaha", weatherDataNaha);

		//		Optional<Weather> getWeatherById = weatherService.getWeatherById(null);
		//		model.addAttribute("weatherDataList", getWeatherById);

		// JDBC
		System.out.println("*** JDBC Start. ***");
		String sql = "select * from weather";
		List<Map<String, Object>> sqlResultList = jdbcTemplate.queryForList(sql);
		sqlResultList.forEach(s -> {
			System.out.println(s);
		});
		System.out.println("*** JDBC End. ***");

		return "hello";
	}

	//idで検索する
	@GetMapping("/weather/process")
	public String getWeatherById(@RequestParam(name = "id") Integer id, Model model) {

		Optional<Weather> weatherData = weatherService.findWetherDataListById(id);
		model.addAttribute("newWeather", weatherData.orElse(new Weather()));
		return "hello";
	}

	//天気記録を追加、更新する
	@PostMapping("/weather/process")
	public String saveWeather(@ModelAttribute @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")  Weather newWeather, Model model) {

		Weather saveWeather = weatherService.saveWeather(newWeather);
		model.addAttribute("newWeather", saveWeather);
		return "hello";
	}

	//天気記録を削除する
	@DeleteMapping("/weather/process")
	public String deleteWeather(@RequestParam(name = "id") Integer id, Model model) {

		weatherService.deleteWeather(id);
		model.addAttribute("newWeather", new Weather());
		return "hello";
	}
}