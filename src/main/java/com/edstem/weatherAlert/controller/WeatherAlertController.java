package com.edstem.weatherAlert.controller;

import com.edstem.weatherAlert.service.WeatherAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherAlertController {
    private final WeatherAlertService weatherAlertService;
    @Autowired
    public WeatherAlertController(WeatherAlertService weatherAlertService){
        this.weatherAlertService = weatherAlertService;
    }

    @GetMapping
    public ResponseEntity<?> getAllAlerts() {
        return ResponseEntity.ok(weatherAlertService.getAllWeatherAlerts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlertsById(@PathVariable Long id) {
        return ResponseEntity.ok(weatherAlertService.getWeatherAlertById(id));
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestAlerts() {
        return ResponseEntity.ok(weatherAlertService.getLatestAlerts());
    }
}