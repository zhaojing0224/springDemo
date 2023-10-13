package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Weather;
import com.example.demo.repository.WeatherRepository;

@Service
@Transactional
public class WeatherService {

	@Autowired
	WeatherRepository weatherRepository;

	/**
	 * レコードを全件取得する。
	 * @return
	 */
	public List<Weather> findAllWeatherData() {

		return weatherRepository.findAll();
	}

	/**
	 * 指定した都市のレコードを取得する。
	 * @param name
	 * @return
	 */
	public List<Weather> findWetherDataListByName(String name) {

		return weatherRepository.findByName(name);
	}

	/**
	 * 指定したidのレコードを取得する
	 * @return
	 */
	public Optional<Weather> findWetherDataListById(Integer id) {

		return weatherRepository.findById(id);
	}

	/**
	 * 天気記録を追加、更新する
	 * @param weather
	 * @return
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

}