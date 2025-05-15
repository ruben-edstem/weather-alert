package com.edstem.weatherAlert.service;

import com.edstem.weatherAlert.model.WeatherAlert;
import com.edstem.weatherAlert.repository.WeatherAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherAlertService {

    private final WeatherAlertRepository weatherAlertRepository;

    @Autowired
    public WeatherAlertService(WeatherAlertRepository weatherAlertRepository) {
        this.weatherAlertRepository = weatherAlertRepository;
    }

    public List<WeatherAlert> getAllWeatherAlerts() {
        return weatherAlertRepository.findAll();
    }

    public WeatherAlert getWeatherAlertById(Long id) {
        return weatherAlertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Weather alert not found with id: " + id));
    }

    public List<WeatherAlert> getLatestAlerts() {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(3);
        return weatherAlertRepository.getLatest(cutoff);
    }

    public WeatherAlert saveWeatherAlert(WeatherAlert weatherAlert) {
        return weatherAlertRepository.save(weatherAlert);
    }

    @Transactional
    public void deleteOldAlerts() {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(3);
        weatherAlertRepository.deleteOlderThan(cutoff);
    }
}
