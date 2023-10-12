package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
  
  List<Weather> findByName(String name);
}