package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Weather;
import com.example.demo.service.WeatherService;

@Controller
public class WeatherController {

	@Autowired
	WeatherService weatherService;

	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * 天気情報編集画面を表示
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/weatherEdit/{id}")
	public String showEditForm(@PathVariable Integer id, Model model) {

		Optional<Weather> optionalWeather = weatherService.findWeatherById(id);
		 // 或者你可以选择其他异常
		Weather weather = optionalWeather.orElseThrow(() -> new RuntimeException("Weather not found"));

		model.addAttribute("weather", weather);
		
		return "weatherEdit";
	}

	/**
	 * 天気情報編集処理
	 * @param weather
	 * @return
	 */
	@PostMapping("/weatherEdit")
	public String weatherEdit(@ModelAttribute Weather weather) {

		weatherService.saveWeather(weather);

		return "redirect:/weatherList";
	}

	/**
	 * 天気情報削除する
	 * @param id
	 * @return
	 */
	@GetMapping("/delWeather/{id}")
	public String delWeather(@PathVariable Integer id) {

		weatherService.deleteWeather(id);

		return "redirect:/weatherList";
	}

	/**
	 * 天気情報新規登録画面を表示
	 * @param id
	 * @return
	 */
	@GetMapping("/addWeather")
	public String addWeather(Model model) {

		Weather weather = new Weather();
		model.addAttribute("weather", weather);

		return "weatherAdd";
	}

	/**
	 * 天気情報新規登録処理
	 * @param weather
	 * @return
	 */
	@PostMapping("/weatherAddProcess")
	public String weatherAddProcess(@ModelAttribute Weather weather) {

		weatherService.saveWeather(weather);

		return "redirect:/weatherList";
	}

	
	/**
	 * 天気情報一覧
	 * @param model
	 * @return
	 */
	@GetMapping("/weatherList")
	public String weather(Model model) {

		model.addAttribute("weather", "天気記録の一覧");

		// 気象データの取得
		List<Weather> weatherDataList = weatherService.findAllWeatherData();
		model.addAttribute("weatherDataList", weatherDataList);

		List<Weather> weatherDataTokyo = weatherService.findWetherDataListByName("東京");
		model.addAttribute("weatherDataTokyo", weatherDataTokyo);

		List<Weather> weatherDataNaha = weatherService.findWetherDataListByName("那覇");
		model.addAttribute("weatherDataNaha", weatherDataNaha);

		return "weatherList";
	}

	/**
	 * idで天気情報一覧検索する
	 * @param searchId
	 * @param model
	 * @return
	 */
	@GetMapping("/weatherSearch")
	public String getWeatherById(@RequestParam(name = "searchId") Integer searchId, Model model) {

		// 気象データの取得
		List<Weather> weatherDataList = weatherService.findWeatherListById(searchId);
		model.addAttribute("weatherDataList", weatherDataList);

		return "weatherList";
	}

	/**
	 * 天気情報一覧戻る
	 * @return
	 */
	@GetMapping("/goBackToWeatherList")
	public String goBackToWeatherList() {
		return "redirect:/weatherList";
	}

}
