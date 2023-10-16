package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Weather;
import com.example.demo.repository.WeatherRepository;

@Service
public class WeatherService {

	@Autowired
	WeatherRepository weatherRepository;

	/**
	 * レコードを全件取得する。
	 * @return Weatherリスト
	 */
	public List<Weather> findAllWeatherData() {

		return weatherRepository.findAll();
	}

	/**
	 * 指定した都市のレコードを取得する。
	 * @param name
	 * @return Weatherリスト
	 */
	public List<Weather> findWetherDataListByName(String name) {

		return weatherRepository.findByName(name);
	}

	/**
	 * 指定したidのレコードを取得する
	 * @return Weather
	 */
	public Optional<Weather> findWetherDataListById(Integer id) {

		return weatherRepository.findById(id);
	}

	/**
	 * 天気記録を更新する
	 * @param weather
	 * @return Weather
	 */

	public Weather saveWeather(Weather weather) {
		return weatherRepository.save(weather);
	}

	/**
	 * 天気記録を削除する
	 * @param weather
	 * @return
	 */
	public void deleteWeather(Integer id) {
		weatherRepository.deleteById(id);
	}

	/**
	 * 指定したidのレコードを取得する
	 * @return
	 */
	public List<Weather> findWeatherListById(Integer id) {

		return weatherRepository.findWeatherListById(id);
	}

	public Optional<Weather> findWeatherById(Integer id) {
		//Optional<Weather> optionalWeather = weatherRepository.findById(id);
		//ModelMapper modelMapper = new ModelMapper();

		// 使用 ModelMapper 进行对象映射
		//return optionalWeather.map(weather -> modelMapper.map(weather, WeatherObj.class));
		return weatherRepository.findById(id);
	}
}